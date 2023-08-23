package com.bank.antifraud.service.impl;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;
import com.bank.antifraud.mappers.SuspiciousCardTransferMapper;
import com.bank.antifraud.repository.SuspiciousCardTransferRepository;
import com.bank.antifraud.service.common.ExceptionReturner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты на сервис подозрительных переводов по номеру карты")
class SuspiciousCardTransferServiceImplTest {
    @Mock
    private SuspiciousCardTransferRepository repository;
    @Mock
    private SuspiciousCardTransferMapper mapper;
    @Mock
    private ExceptionReturner returner;
    @InjectMocks
    private SuspiciousCardTransferServiceImpl service;

    @Test
    @DisplayName("Создание, позитивный сценарий")
    void savePositiveTest() {
        long id = 3L;
        SuspiciousCardTransferDto cardDto = getSuspiciousCardTransferDto(id);
        SuspiciousCardTransferEntity cardEntity = getSuspiciousCardTransferEntity(id);
        when(mapper.toEntity(cardDto)).thenReturn(cardEntity);
        when(repository.save(cardEntity)).thenReturn(cardEntity);
        when(mapper.toDto(cardEntity)).thenReturn(cardDto);

        SuspiciousCardTransferDto actualCardDto = service.save(cardDto);

        assertThat(actualCardDto).isEqualTo(cardDto);
        verify(repository).save(cardEntity);
        verify(mapper).toEntity(cardDto);
        verify(mapper).toDto(cardEntity);
    }

    @Test
    @DisplayName("Сохранение по существующему id, негативный сценарий")
    void saveByExistingIdNegativeTest() {
        long id = 3L;
        SuspiciousCardTransferDto existingDto = new SuspiciousCardTransferDto(
                2L,
                222L,
                true,
                true,
                "block",
                "suspicious"
        );
        SuspiciousCardTransferDto actualDto = getSuspiciousCardTransferDto(id);
        SuspiciousCardTransferEntity actualEntity = getSuspiciousCardTransferEntity(id);
        when(mapper.toEntity(actualDto)).thenReturn(actualEntity);
        when(repository.save(actualEntity)).thenThrow(new IllegalArgumentException("test"));

        assertThrows(IllegalArgumentException.class, () -> service.save(actualDto));
        assertEquals(actualDto.getCardTransferId(), existingDto.getCardTransferId());
        verify(mapper).toEntity(actualDto);
        verify(repository).save(actualEntity);
    }

    @Test
    @DisplayName("Чтение по id, позитивный сценарий")
    void findByIdPositiveTest() {
        long id = 3L;
        SuspiciousCardTransferDto cardTransferDto = getSuspiciousCardTransferDto(id);
        SuspiciousCardTransferEntity cardTransferEntity = getSuspiciousCardTransferEntity(id);

        doReturn(Optional.of(cardTransferEntity)).when(repository).findById(id);
        doReturn(cardTransferDto).when(mapper).toDto(cardTransferEntity);

        SuspiciousCardTransferDto actualCardDto = service.findById(id);
        assertThat(actualCardDto).isEqualTo(cardTransferDto);
        verify(repository).findById(id);
        verify(mapper).toDto(cardTransferEntity);
    }

    @Test
    @DisplayName("Чтение по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeNest() {
        long id = 3L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(returner.getEntityNotFoundException(any()))
                .thenThrow(new EntityNotFoundException("SuspiciousCardTransfer по данному id не существует"));

        assertThrows(EntityNotFoundException.class, () -> service.findById(id));
        verify(repository).findById(id);
        verify(returner).getEntityNotFoundException(any());
        verifyNoInteractions(mapper);
    }

    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        long id = 3L;
        SuspiciousCardTransferDto cardTransferDto = getSuspiciousCardTransferDto(id);
        SuspiciousCardTransferEntity cardTransferEntity = getSuspiciousCardTransferEntity(id);

        doReturn(Optional.of(cardTransferEntity)).when(repository).findById(id);
        doReturn(cardTransferEntity).when(mapper).mergeToEntity(cardTransferDto, cardTransferEntity);
        doReturn(cardTransferEntity).when(repository).save(cardTransferEntity);
        doReturn(cardTransferDto).when(mapper).toDto(cardTransferEntity);

        SuspiciousCardTransferDto actualCardDto = service.update(id, cardTransferDto);

        assertThat(actualCardDto).isEqualTo(cardTransferDto);
        verify(mapper).mergeToEntity(cardTransferDto, cardTransferEntity);
        verify(repository).save(cardTransferEntity);
        verify(mapper).toDto(cardTransferEntity);
    }

    @Test
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        long id = 3L;
        SuspiciousCardTransferDto cardTransferDto = getSuspiciousCardTransferDto(id);
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(returner.getEntityNotFoundException(any()))
                .thenThrow(new EntityNotFoundException("SuspiciousCardTransfer по данному id не существует"));

        assertThrows(EntityNotFoundException.class, () -> service.update(id, cardTransferDto));
        verify(repository).findById(id);
        verify(returner).getEntityNotFoundException(any());
        verifyNoInteractions(mapper);
    }

    @Test
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void findAllByIdsPositiveTest() {
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L, 3L));
        List<SuspiciousCardTransferDto> expectedListDto = new ArrayList<>();
        expectedListDto.add(new SuspiciousCardTransferDto(1L, 345L, false,
                false, "dont block", "dont suspicious"));
        expectedListDto.add(new SuspiciousCardTransferDto(2L, 231L, true,
                false, "dont block", "dont suspicious"));
        expectedListDto.add(new SuspiciousCardTransferDto(3L, 111L, false,
                true, "dont block", "dont suspicious"));

        List<SuspiciousCardTransferEntity> listEntity = new ArrayList<>();
        listEntity.add(new SuspiciousCardTransferEntity(1L, 345L, false,
                false, "dont block", "dont suspicious"));
        listEntity.add(new SuspiciousCardTransferEntity(2L, 231L, true,
                false, "dont block", "dont suspicious"));
        listEntity.add(new SuspiciousCardTransferEntity(3L, 111L, false,
                true, "dont block", "dont suspicious"));

        IntStream.range(0, ids.size())
                .forEach(i -> doReturn(Optional.of(listEntity.get(i))).when(repository).findById(ids.get(i)));
        doReturn(expectedListDto).when(mapper).toListDto(listEntity);

        List<SuspiciousCardTransferDto> actualListDto = service.findAllById(ids);

        assertThat(actualListDto).isEqualTo(expectedListDto);
        IntStream.range(0, ids.size())
                .forEach(i -> verify(repository).findById(ids.get(i)));
        verify(mapper).toListDto(listEntity);
    }

    @Test
    @DisplayName("Чтение по нескольким несуществующим id, негативный сценарий")
    void findAllByNonExistIdsNegativeTest() {
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L, 3L));
        doReturn(Optional.empty()).when(repository).findById(ids.get(0));
        when(returner.getEntityNotFoundException(any()))
                .thenThrow(new EntityNotFoundException("SuspiciousCardTransfer по данному id не существует"));
        assertThrows(EntityNotFoundException.class, () -> service.findAllById(ids));
        verify(repository).findById(ids.get(0));
        verifyNoInteractions(mapper);
    }

    static SuspiciousCardTransferDto getSuspiciousCardTransferDto(long id) {
        return new SuspiciousCardTransferDto(
                id,
                222L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );
    }

    static SuspiciousCardTransferEntity getSuspiciousCardTransferEntity(long id) {
        return new SuspiciousCardTransferEntity(
                id,
                111L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );
    }
}