package com.bank.transfer.service;

import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.entity.PhoneTransferEntity;
import com.bank.transfer.mapper.PhoneTransferMapper;
import com.bank.transfer.repository.PhoneTransferRepository;
import com.bank.transfer.service.Impl.PhoneTransferServiceImpl;
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
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PhoneTransferServiceImplTest {
    
    @InjectMocks
    private PhoneTransferServiceImpl phoneTransferServiceImpl;
    @Mock
    private PhoneTransferRepository repository;
    @Mock
    private PhoneTransferMapper mapper;
    @Mock
    private EntityNotFoundReturner notFoundReturner;

    @Test
    @DisplayName("Поиск по id, позитивный сценарий")
    void findByIdPositiveTest(){
        Mockito.when(repository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(getPhoneTransferEntity()));
        Mockito.when(mapper.toDto(ArgumentMatchers.any(PhoneTransferEntity.class)))
                .thenReturn(getPhoneTransferDto());

        PhoneTransferDto phoneTransferDtoActual = phoneTransferServiceImpl
                .findById(ArgumentMatchers.anyLong());
        PhoneTransferDto phoneTransferDtoExpected = getPhoneTransferDto();

        Assertions.assertEquals(phoneTransferDtoExpected, phoneTransferDtoActual);
    }

    @Test
    @DisplayName("Поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeTest(){
        Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        Mockito.when(notFoundReturner.getEntityNotFoundException(ArgumentMatchers.anyLong(), ArgumentMatchers.anyString()))
                .thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> phoneTransferServiceImpl.findById(ArgumentMatchers.anyLong())
                );

    }

    @Test
    @DisplayName("Поиск по нескольким id, позитивный сценарий")
    void findAllByIdPositiveTest(){
        Mockito.when(repository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(getPhoneTransferEntity()));
        Mockito.when(mapper.toDtoList(ArgumentMatchers.anyList()))
                .thenReturn(List.of(getPhoneTransferDto()));

        List<PhoneTransferDto> phoneTransferDtoActual =
                phoneTransferServiceImpl.findAllById(List.of(1L));
        List<PhoneTransferDto> phoneTransferDtoExpected =
                List.of(getPhoneTransferDto());

        Assertions.assertIterableEquals(phoneTransferDtoExpected, phoneTransferDtoActual);
    }

    @Test
    @DisplayName("Поиск по нескольким несуществующим id, негативный сценарий")
    void findAllByNonExistIdNegativeTest(){
        Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        Mockito.when(notFoundReturner.getEntityNotFoundException(ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyString()))
                .thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> phoneTransferServiceImpl.findAllById(List.of(1L))
        );
    }

    @Test
    @DisplayName("Сохранение пользователя")
    void saveTransferPositiveTest(){
        Mockito.when(mapper.toEntity(ArgumentMatchers.any(PhoneTransferDto.class)))
                .thenReturn(getPhoneTransferEntity());

        Mockito.when(repository.save(ArgumentMatchers.any(PhoneTransferEntity.class)))
                .thenReturn(getPhoneTransferEntity());

        Mockito.when(mapper.toDto(ArgumentMatchers.any(PhoneTransferEntity.class)))
                .thenReturn(getPhoneTransferDto());

        PhoneTransferDto phoneTransferDtoActual = phoneTransferServiceImpl.save(getPhoneTransferDto());
        PhoneTransferDto phoneTransferDtoExpected = getPhoneTransferDto();

        Assertions.assertEquals(phoneTransferDtoExpected, phoneTransferDtoActual);
    }

    @Test
    @DisplayName("Сохранение при получении null, негативный сценарий")
    void saveTransferNullNegativeTest(){

        Mockito.when(mapper.toEntity(null)).thenReturn(null);
        Mockito.when(repository.save(ArgumentMatchers.any())).thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> phoneTransferServiceImpl.save(ArgumentMatchers.any())
        );
    }

    @Test
    @DisplayName("Обновление, позитивный сценарий")
    void updateTransferPositiveTest(){
        Mockito.when(repository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(getPhoneTransferEntity()));
        Mockito.when(repository.save(ArgumentMatchers.any(PhoneTransferEntity.class)))
                .thenReturn(getPhoneTransferEntity());

        Mockito.when(mapper.mergeToEntity(getPhoneTransferDto(), getPhoneTransferEntity()))
                .thenReturn(getPhoneTransferEntity());
        Mockito.when(mapper.toDto(ArgumentMatchers.any(PhoneTransferEntity.class)))
                .thenReturn(getPhoneTransferDto());

        PhoneTransferDto phoneTransferDtoActual =
                phoneTransferServiceImpl.update(ArgumentMatchers.anyLong(), getPhoneTransferDto());
        PhoneTransferDto phoneTransferDtoExpected = getPhoneTransferDto();

        Assertions.assertEquals(phoneTransferDtoExpected, phoneTransferDtoActual);
    }

    @Test
    @DisplayName("Обновление при получении null, негативный сценарий")
    void updateNullNegativeTest(){
        Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        Mockito.when(notFoundReturner.getEntityNotFoundException(ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyString()))
                .thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> phoneTransferServiceImpl.update(ArgumentMatchers.anyLong(), getPhoneTransferDto())
        );
    }

    private PhoneTransferDto getPhoneTransferDto() {
        return new PhoneTransferDto(
                1L,
                2L,
                BigDecimal.TEN,
                "purpose",
                3L
        );
    }

    private PhoneTransferEntity getPhoneTransferEntity() {
        return new PhoneTransferEntity(
                1L,
                2L,
                BigDecimal.TEN,
                "purpose",
                3L
        );
    }
}
