package com.bank.antifraud.service.impl;

import com.bank.antifraud.dto.SuspiciousAccountTransferDto;
import com.bank.antifraud.entity.SuspiciousAccountTransferEntity;
import com.bank.antifraud.mappers.SuspiciousAccountTransferMapper;
import com.bank.antifraud.repository.SuspiciousAccountTransferRepository;
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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты на сервис подозрительных переводов по номеру счета")
class SuspiciousAccountTransferServiceImplTest {
    @Mock
    private SuspiciousAccountTransferRepository repository;
    @Mock
    private SuspiciousAccountTransferMapper mapper;
    @Mock
    private ExceptionReturner returner;
    @InjectMocks
    private SuspiciousAccountTransferServiceImpl service;

    @Test
    @DisplayName("Сохранение, позитивный сценарий")
    void savePositiveTest() {
        long id = 3L;
        SuspiciousAccountTransferDto accountDto = getSuspiciousAccountTransferDto(id);
        SuspiciousAccountTransferEntity accountEntity = getSuspiciousAccountTransferEntity(id);
        when(mapper.toEntity(accountDto)).thenReturn(accountEntity);
        when(repository.save(accountEntity)).thenReturn(accountEntity);
        when(mapper.toDto(accountEntity)).thenReturn(accountDto);

        SuspiciousAccountTransferDto actualAccountDto = service.save(accountDto);

        assertThat(actualAccountDto).isEqualTo(accountDto);
        verify(repository).save(accountEntity);
        verify(mapper).toEntity(accountDto);
        verify(mapper).toDto(accountEntity);
    }

    @Test
    @DisplayName("Сохранение по существующему id, негативный сценарий")
    void saveByExistingIdNegativeTest() {
        long id = 3L;
        SuspiciousAccountTransferDto existingDto = new SuspiciousAccountTransferDto(
                2L,
                345L,
                true,
                true,
                "block",
                "suspicious"
        );
        SuspiciousAccountTransferDto actualDto = getSuspiciousAccountTransferDto(id);
        SuspiciousAccountTransferEntity actualEntity = getSuspiciousAccountTransferEntity(id);
        when(mapper.toEntity(actualDto)).thenReturn(actualEntity);
        when(repository.save(actualEntity)).thenThrow(new IllegalArgumentException("test"));

        assertThrows(IllegalArgumentException.class, () -> service.save(actualDto));
        assertEquals(actualDto.getAccountTransferId(), existingDto.getAccountTransferId());
        verify(mapper).toEntity(actualDto);
        verify(repository).save(actualEntity);
    }

    @Test
    @DisplayName("Поиск по id, позитивный сценарий")
    void findByIdPositiveTest() {
        long id = 3L;
        SuspiciousAccountTransferDto accountTransferDto = getSuspiciousAccountTransferDto(id);
        SuspiciousAccountTransferEntity accountTransferEntity = getSuspiciousAccountTransferEntity(id);

        doReturn(Optional.of(accountTransferEntity)).when(repository).findById(id);
        doReturn(accountTransferDto).when(mapper).toDto(accountTransferEntity);

        SuspiciousAccountTransferDto actualAccountDto = service.findById(id);
        assertThat(actualAccountDto).isEqualTo(accountTransferDto);
        verify(repository).findById(id);
        verify(mapper).toDto(accountTransferEntity);
    }

    @Test
    @DisplayName("Поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeNest() {
        long id = 3L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(returner.getEntityNotFoundException(any()))
                .thenThrow(new EntityNotFoundException("SuspiciousAccountTransfer по данному id не существует"));
        assertThrows(EntityNotFoundException.class, () -> service.findById(id));
        verify(repository).findById(id);
        verify(returner).getEntityNotFoundException(any());
        verifyNoInteractions(mapper);
    }

    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        long id = 3L;
        SuspiciousAccountTransferDto accountTransferDto = getSuspiciousAccountTransferDto(id);
        SuspiciousAccountTransferEntity accountTransferEntity = getSuspiciousAccountTransferEntity(id);

        doReturn(Optional.of(accountTransferEntity)).when(repository).findById(id);
        doReturn(accountTransferEntity).when(mapper).mergeToEntity(accountTransferDto, accountTransferEntity);
        doReturn(accountTransferEntity).when(repository).save(accountTransferEntity);
        doReturn(accountTransferDto).when(mapper).toDto(accountTransferEntity);

        SuspiciousAccountTransferDto actualAccountDto = service.update(id, accountTransferDto);

        assertThat(actualAccountDto).isEqualTo(accountTransferDto);
        verify(mapper).mergeToEntity(accountTransferDto, accountTransferEntity);
        verify(repository).save(accountTransferEntity);
        verify(mapper).toDto(accountTransferEntity);
    }

    @Test
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        long id = 3L;
        SuspiciousAccountTransferDto accountTransferDto = getSuspiciousAccountTransferDto(id);
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(returner.getEntityNotFoundException(any()))
                .thenThrow(new EntityNotFoundException("SuspiciousAccountTransfer по данному id не существует"));

        assertThrows(EntityNotFoundException.class, () -> service.update(id, accountTransferDto));
        verify(repository).findById(id);
        verify(returner).getEntityNotFoundException(any());
        verifyNoInteractions(mapper);
    }

    @Test
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void findAllByIdsPositiveTest() {
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L, 3L));
        List<SuspiciousAccountTransferDto> expectedListDto = new ArrayList<>();
        expectedListDto.add(new SuspiciousAccountTransferDto(1L, 345L, false,
                false, "dont block", "dont suspicious"));
        expectedListDto.add(new SuspiciousAccountTransferDto(2L, 231L, true,
                false, "dont block", "dont suspicious"));
        expectedListDto.add(new SuspiciousAccountTransferDto(3L, 111L, false,
                true, "dont block", "dont suspicious"));

        List<SuspiciousAccountTransferEntity> listEntity = new ArrayList<>();
        listEntity.add(new SuspiciousAccountTransferEntity(1L, 345L, false,
                false, "dont block", "dont suspicious"));
        listEntity.add(new SuspiciousAccountTransferEntity(2L, 231L, true,
                false, "dont block", "dont suspicious"));
        listEntity.add(new SuspiciousAccountTransferEntity(3L, 111L, false,
                true, "dont block", "dont suspicious"));

        IntStream.range(0, ids.size())
                .forEach(i -> doReturn(Optional.of(listEntity.get(i))).when(repository).findById(ids.get(i)));
        doReturn(expectedListDto).when(mapper).toListDto(listEntity);

        List<SuspiciousAccountTransferDto> actualListDto = service.findAllById(ids);

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
                .thenThrow(new EntityNotFoundException("SuspiciousAccountTransfer по данному id не существует"));
        assertThrows(EntityNotFoundException.class, () -> service.findAllById(ids));
        verify(repository).findById(ids.get(0));
        verifyNoInteractions(mapper);
    }

    static SuspiciousAccountTransferDto getSuspiciousAccountTransferDto(long id) {
        return new SuspiciousAccountTransferDto(
                id,
                345L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );
    }

    static SuspiciousAccountTransferEntity getSuspiciousAccountTransferEntity(long id) {
        return new SuspiciousAccountTransferEntity(
                id,
                345L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );
    }
}