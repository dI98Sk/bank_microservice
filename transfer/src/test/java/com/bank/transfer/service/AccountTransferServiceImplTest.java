package com.bank.transfer.service;


import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.entity.AccountTransferEntity;
import com.bank.transfer.entity.AuditEntity;
import com.bank.transfer.mapper.AccountTransferMapper;
import com.bank.transfer.repository.AccountTransferRepository;
import com.bank.transfer.service.Impl.AccountTransferServiceImpl;
import com.bank.transfer.service.common.EntityNotFoundReturner;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AccountTransferServiceImplTest {

    @InjectMocks
    private AccountTransferServiceImpl accountTransferServiceImpl;
    @Mock
    private AccountTransferRepository repository;
    @Mock
    private AccountTransferMapper mapper;
    @Mock
    private EntityNotFoundReturner notFoundReturner;

    @Test
    @DisplayName("Поиск по id, позитивный сценарий")
    void findByIdPositiveTest(){

        Mockito.when(repository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(getAccountTransferEntity()));
        Mockito.when(mapper.toDto(ArgumentMatchers.any(AccountTransferEntity.class)))
                .thenReturn(getAccountTransferDto());

        AccountTransferDto accountTransferDtoActual = accountTransferServiceImpl.findById(ArgumentMatchers.anyLong());
        AccountTransferDto accountTransferDtoExpected = getAccountTransferDto();

        Assertions.assertEquals(accountTransferDtoExpected,accountTransferDtoActual);
    }


    @Test
    @DisplayName("Поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeTest() {
        Mockito.when(repository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());
        Mockito.when(notFoundReturner.getEntityNotFoundException(ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyString()))
                .thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> accountTransferServiceImpl.findById(ArgumentMatchers.anyLong())
        );
    }


    @Test
    @DisplayName("Поиск по нескольким id, позитивный сценарий")
    void findAllByIdPositiveTest() {

        Mockito.when(repository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(getAccountTransferEntity()));
        Mockito.when(mapper.toDtoList(ArgumentMatchers.anyList()))
                .thenReturn(List.of(getAccountTransferDto()));

        List<AccountTransferDto> accountTransferDtoExpected = List.of(getAccountTransferDto());
        List<AccountTransferDto> accountTransferDtoActual = accountTransferServiceImpl.findAllById(List.of(1L));

        Assertions.assertIterableEquals(accountTransferDtoExpected, accountTransferDtoActual);

    }

    @Test
    @DisplayName("Поиск по нескольким несуществующим id, негативный сценарий")
    void findAllByNonExistIdNegativeTest() {

        Mockito.when(repository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());
        Mockito.when(notFoundReturner.getEntityNotFoundException(ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyString()))
                .thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> accountTransferServiceImpl.findAllById(List.of(1L))
        );
    }

    @Test
    @DisplayName("Сохранение пользователя")
    void saveTransferPositiveTest(){

        Mockito.when(mapper.toEntity(ArgumentMatchers.any(AccountTransferDto.class)))
                .thenReturn(getAccountTransferEntity());

        Mockito.when(repository.save(ArgumentMatchers.any(AccountTransferEntity.class)))
                .thenReturn(getAccountTransferEntity());

        Mockito.when(mapper.toDto(ArgumentMatchers.any(AccountTransferEntity.class)))
                .thenReturn(getAccountTransferDto());

        AccountTransferDto accountTransferDtoActual = accountTransferServiceImpl.save(getAccountTransferDto());
        AccountTransferDto accountTransferDtoExpected = getAccountTransferDto();

        Assertions.assertEquals(accountTransferDtoExpected, accountTransferDtoActual);
    }

    @Test
    @DisplayName("Сохранение при получении null, негативный сценарий")
    void saveTransferNullNegativeTest() {

        Mockito.when(mapper.toEntity(null)).thenReturn(null);
        Mockito.when(repository.save(ArgumentMatchers.any())).thenThrow(RuntimeException.class);

        Assertions.assertThrows(RuntimeException.class,
                () -> accountTransferServiceImpl.save(ArgumentMatchers.any())
        );
    }

    @Test
    @DisplayName("Обновление, позитивный сценарий")
    void updateTransferPositiveTest(){

        Mockito.when(repository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(getAccountTransferEntity()));
        Mockito.when(repository.save(ArgumentMatchers.any(AccountTransferEntity.class)))
                .thenReturn(getAccountTransferEntity());

        Mockito.when(mapper.mergeToEntity(getAccountTransferDto(), getAccountTransferEntity()))
                .thenReturn(getAccountTransferEntity());
        Mockito.when(mapper.toDto(ArgumentMatchers.any(AccountTransferEntity.class)))
                .thenReturn(getAccountTransferDto());

        AccountTransferDto accountTransferDtoActual = accountTransferServiceImpl
                .update(ArgumentMatchers.anyLong(), getAccountTransferDto());
        AccountTransferDto accountTransferDtoExpected = getAccountTransferDto();

        Assertions.assertEquals(accountTransferDtoExpected, accountTransferDtoActual);

    }

    @Test
    @DisplayName("Обновление при получении null, негативный сценарий")
    void updateNullNegativeTest() {
        Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        Mockito.when(notFoundReturner.getEntityNotFoundException(ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyString()))
                .thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> accountTransferServiceImpl.update(ArgumentMatchers.anyLong(),getAccountTransferDto())
        );

    }

    private AccountTransferEntity getAccountTransferEntity() {

        return new AccountTransferEntity(
                1L,
                1L,
                BigDecimal.ONE,
                "purpose",
                2L
        );
    }

    private AccountTransferDto getAccountTransferDto() {
        return new AccountTransferDto(
                1L,
                1L,
                BigDecimal.ONE,
                "purpose",
                2L
        );
    }

}
