package com.bank.account.service;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;
import com.bank.account.mapper.AccountDetailsMapper;
import com.bank.account.repository.AccountDetailsRepository;
import com.bank.account.service.common.ExceptionReturner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AccountDetailsServiceImplTest {
    @InjectMocks
    private AccountDetailsServiceImpl accountDetailsService;
    @Mock
    private AccountDetailsRepository repository;
    @Mock
    private AccountDetailsMapper mapper;
    @Mock
    private ExceptionReturner exceptionReturner;

    @Test
    @DisplayName("поиск по id, позитивный сценарий")
    void findByIdPositiveTest() {
        Mockito.when(repository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(getAccountDetailsEntity()));
        Mockito.when(mapper.toDto(ArgumentMatchers.any(AccountDetailsEntity.class)))
                .thenReturn(getAccountDetailsDto());

        AccountDetailsDto accountDetailsDtoActual = accountDetailsService.findById(ArgumentMatchers.anyLong());
        AccountDetailsDto accountDetailsDtoExpected = getAccountDetailsDto();

        Assertions.assertEquals(accountDetailsDtoExpected, accountDetailsDtoActual);
    }

    @Test
    @DisplayName("поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeTest() {
        Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        Mockito.when(exceptionReturner.getEntityNotFoundException(ArgumentMatchers.anyString()))
                .thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> accountDetailsService.findById(ArgumentMatchers.anyLong()));
    }

    @Test
    @DisplayName("Поиск по нескольким id, позитивный сценарий")
    void findAllByIdPositiveTest() {

        Mockito.when(repository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(getAccountDetailsEntity()));
        Mockito.when(mapper.toDtoList(ArgumentMatchers.anyList()))
                .thenReturn(List.of(getAccountDetailsDto()));

        List<AccountDetailsDto> accountDetailsDtoExpected = List.of(getAccountDetailsDto());
        List<AccountDetailsDto> accountDetailsDtoActual = accountDetailsService.findAllById(List.of(1L));
        Assertions.assertIterableEquals(accountDetailsDtoExpected, accountDetailsDtoActual);
    }

    @Test
    @DisplayName("Поиск по нескольким несуществующим id, негативный сценарий")
    void findAllByNonExistIdNegativeTest() {
        Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        Mockito.when(exceptionReturner.getEntityNotFoundException(ArgumentMatchers.anyString()))
                .thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> accountDetailsService.findAllById(List.of(1L)));
    }

    @Test
    @DisplayName("Сохранение, позитивный сценарий")
    void savePositiveTest() {
        Mockito.when(mapper.toEntity(ArgumentMatchers.any(AccountDetailsDto.class)))
                .thenReturn(getAccountDetailsEntity());
        Mockito.when(repository.save(ArgumentMatchers.any(AccountDetailsEntity.class)))
                .thenReturn(getAccountDetailsEntity());
        Mockito.when(mapper.toDto(ArgumentMatchers.any(AccountDetailsEntity.class)))
                .thenReturn(getAccountDetailsDto());

        AccountDetailsDto accountDetailsDtoActual = accountDetailsService.save(getAccountDetailsDto());
        AccountDetailsDto accountDetailsDtoExpected = getAccountDetailsDto();
        Assertions.assertEquals(accountDetailsDtoExpected, accountDetailsDtoActual);
    }

    @Test
    @DisplayName("Сохранение при получении null, негативный сценарий")
    void saveNullNegativeTest() {
        Mockito.when(mapper.toEntity(null))
                .thenReturn(null);
        Mockito.when(repository.save(ArgumentMatchers.any()))
                .thenThrow(RuntimeException.class);
        Assertions.assertThrows(RuntimeException.class,
                () -> accountDetailsService.save(ArgumentMatchers.any()));
    }

    @Test
    @DisplayName("Обновление, позитивный сценарий")
    void updatePositiveTest() {

        Mockito.when(repository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(getAccountDetailsEntity()));
        Mockito.when(repository.save(ArgumentMatchers.any(AccountDetailsEntity.class)))
                .thenReturn(getAccountDetailsEntity());

        Mockito.when(mapper.mergeToEntity(getAccountDetailsEntity(), getAccountDetailsDto()))
                .thenReturn(getAccountDetailsEntity());
        Mockito.when(mapper.toDto(ArgumentMatchers.any(AccountDetailsEntity.class)))
                .thenReturn(getAccountDetailsDto());

        AccountDetailsDto accountDetailsDtoActual = accountDetailsService
                .update(ArgumentMatchers.anyLong(), getAccountDetailsDto());
        AccountDetailsDto accountDetailsDtoExpected = getAccountDetailsDto();
        Assertions.assertEquals(accountDetailsDtoExpected, accountDetailsDtoActual);
    }

    @Test
    @DisplayName("Обновление при получении null, негативный сценарий")
    void updateNullNegativeTest() {
        Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        Mockito.when(exceptionReturner.getEntityNotFoundException(ArgumentMatchers.anyString()))
                .thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> accountDetailsService.update(ArgumentMatchers.anyLong(), getAccountDetailsDto()));
    }

    private AccountDetailsEntity getAccountDetailsEntity() {
        return new AccountDetailsEntity(
                1L,
                1L,
                1L,
                1L,
                BigDecimal.TEN,
                false,
                1L
        );
    }

    private AccountDetailsDto getAccountDetailsDto() {
        return new AccountDetailsDto(
                1L,
                1L,
                1L,
                1L,
                BigDecimal.TEN,
                false,
                1L
        );
    }
}