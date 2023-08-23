package com.bank.transfer.service;

import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.entity.CardTransferEntity;
import com.bank.transfer.mapper.CardTransferMapper;
import com.bank.transfer.repository.CardTransferRepository;
import com.bank.transfer.service.Impl.CardTransferServiceImpl;
import com.bank.transfer.service.common.EntityNotFoundReturner;
import liquibase.pro.packaged.M;
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
public class CardTransferServiceImplTest {

    @InjectMocks
    private CardTransferServiceImpl cardTransferService;

    @Mock
    private CardTransferRepository repository;

    @Mock
    private CardTransferMapper mapper;

    @Mock
    private EntityNotFoundReturner notFoundReturner;


    @Test
    @DisplayName("Поиск по id, позитивный сценарий")
    void findByIdPositiveTest(){
        Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(getCardTransferEntity()));
        Mockito.when(mapper.toDto(ArgumentMatchers.any(CardTransferEntity.class)))
                .thenReturn(getCardTransferDto());

        CardTransferDto cardTransferDtoActual = cardTransferService.findById(ArgumentMatchers.anyLong());
        CardTransferDto cardTransferDtoExpected = getCardTransferDto();

        Assertions.assertEquals(cardTransferDtoExpected, cardTransferDtoActual);
    }

    @Test
    @DisplayName("Поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeTest() {
        Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        Mockito.when(notFoundReturner.getEntityNotFoundException(ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyString()))
                .thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(
              EntityNotFoundException.class,
                () -> cardTransferService.findById(ArgumentMatchers.anyLong())
        );
    }

    @Test
    @DisplayName("Поиск по нескольким id, позитивный сценарий")
    void findAllByIdPositiveTest(){
        Mockito.when(repository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(getCardTransferEntity()));
        Mockito.when(mapper.toDtoList(ArgumentMatchers.anyList()))
                .thenReturn(List.of(getCardTransferDto()));

        List<CardTransferDto> cardTransferDtoActual = cardTransferService.findAllById(List.of(1L));
        List<CardTransferDto> cardTransferDtoExpected = List.of(getCardTransferDto());

        Assertions.assertIterableEquals(cardTransferDtoExpected, cardTransferDtoActual);
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
                () -> cardTransferService.findAllById(List.of(1L))
        );
    }

    @Test
    @DisplayName("Сохранение пользователя")
    void saveTransferPositiveTest(){
        Mockito.when(mapper.toEntity(ArgumentMatchers.any(CardTransferDto.class)))
                .thenReturn(getCardTransferEntity());

        Mockito.when(repository.save(ArgumentMatchers.any(CardTransferEntity.class)))
                .thenReturn(getCardTransferEntity());

        Mockito.when(mapper.toDto(ArgumentMatchers.any(CardTransferEntity.class)))
                .thenReturn(getCardTransferDto());

        CardTransferDto cardTransferDtoActual = cardTransferService.save(getCardTransferDto());
        CardTransferDto cardTransferDtoExpected = getCardTransferDto();

        Assertions.assertEquals(cardTransferDtoExpected, cardTransferDtoActual);
    }

    @Test
    @DisplayName("Сохранение при получении null, негативный сценарий")
    void saveTransferNullNegativeTest(){

        Mockito.when(mapper.toEntity(null)).thenReturn(null);
        Mockito.when(repository.save(ArgumentMatchers.any())).thenThrow(RuntimeException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> cardTransferService.save(ArgumentMatchers.any())
        );
    }

    @Test
    @DisplayName("Обновление, позитивный сценарий")
    void updateTransferPositiveTest(){
        Mockito.when(repository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(getCardTransferEntity()));
        Mockito.when(repository.save(ArgumentMatchers.any(CardTransferEntity.class)))
                .thenReturn(getCardTransferEntity());

        Mockito.when(mapper.mergeToEntity(getCardTransferDto(), getCardTransferEntity()))
                .thenReturn(getCardTransferEntity());
        Mockito.when(mapper.toDto(ArgumentMatchers.any(CardTransferEntity.class)))
                .thenReturn(getCardTransferDto());

        CardTransferDto cardTransferDtoActual = cardTransferService
                .update(ArgumentMatchers.anyLong(), getCardTransferDto());
        CardTransferDto cardTransferDtoExpected = getCardTransferDto();

        Assertions.assertEquals(cardTransferDtoExpected, cardTransferDtoActual);
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
                () -> cardTransferService.update(ArgumentMatchers.anyLong(), getCardTransferDto())
        );
    }

    private CardTransferDto getCardTransferDto() {
        return new CardTransferDto(
                1L,
                2L,
                BigDecimal.TEN,
                "purpose",
                3L
        );
    }

    private CardTransferEntity getCardTransferEntity() {
        return new CardTransferEntity(
                1L,
                2L,
                BigDecimal.TEN,
                "purpose",
                3L
        );
    }
}
