package com.bank.antifraud.service.impl;

import com.bank.antifraud.dto.SuspiciousPhoneTransferDto;
import com.bank.antifraud.entity.SuspiciousPhoneTransferEntity;
import com.bank.antifraud.mappers.SuspiciousPhoneTransferMapper;
import com.bank.antifraud.repository.SuspiciousPhoneTransferRepository;
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
@DisplayName("Тесты на сервис подозрительных переводов по номеру телефона")
class SuspiciousPhoneTransferServiceImplTest {
    @Mock
    private SuspiciousPhoneTransferRepository repository;
    @Mock
    private SuspiciousPhoneTransferMapper mapper;
    @Mock
    private ExceptionReturner returner;
    @InjectMocks
    private SuspiciousPhoneTransferServiceImpl service;

    @Test
    @DisplayName("Создание, позитивный сценарий")
    void savePositiveTest() {
        long id = 3l;
        SuspiciousPhoneTransferDto phoneTransferDto = getSuspiciousPhoneTransferDto(id);
        SuspiciousPhoneTransferEntity phoneTransferEntity = getSuspiciousPhoneTransferEntity(id);
        when(mapper.toEntity(phoneTransferDto)).thenReturn(phoneTransferEntity);
        when(repository.save(phoneTransferEntity)).thenReturn(phoneTransferEntity);
        when(mapper.toDto(phoneTransferEntity)).thenReturn(phoneTransferDto);

        SuspiciousPhoneTransferDto actualPhoneTransferDto = service.save(phoneTransferDto);

        assertThat(actualPhoneTransferDto).isEqualTo(phoneTransferDto);
        verify(repository).save(phoneTransferEntity);
        verify(mapper).toEntity(phoneTransferDto);
        verify(mapper).toDto(phoneTransferEntity);
    }

    @Test
    @DisplayName("Сохранение по существующему id, негативный сценарий")
    void saveByExistingIdNegativeTest() {
        long id = 3l;
        SuspiciousPhoneTransferDto existingDto = new SuspiciousPhoneTransferDto(
                2l,
                222L,
                true,
                true,
                "block",
                "suspicious"
        );
        SuspiciousPhoneTransferDto actualDto = getSuspiciousPhoneTransferDto(id);
        SuspiciousPhoneTransferEntity actualEntity = getSuspiciousPhoneTransferEntity(id);
        when(mapper.toEntity(actualDto)).thenReturn(actualEntity);
        when(repository.save(actualEntity)).thenThrow(new IllegalArgumentException("test"));

        assertThrows(IllegalArgumentException.class, () -> service.save(actualDto));
        assertEquals(actualDto.getPhoneTransferId(), existingDto.getPhoneTransferId());
        verify(mapper).toEntity(actualDto);
        verify(repository).save(actualEntity);
    }

    @Test
    @DisplayName("Чтение по id, позитивный сценарий")
    void findByIdPositiveTest() {
        long id = 3l;
        SuspiciousPhoneTransferDto phoneTransferDto = getSuspiciousPhoneTransferDto(id);
        SuspiciousPhoneTransferEntity phoneTransferEntity = getSuspiciousPhoneTransferEntity(id);

        doReturn(Optional.of(phoneTransferEntity)).when(repository).findById(id);
        doReturn(phoneTransferDto).when(mapper).toDto(phoneTransferEntity);

        SuspiciousPhoneTransferDto actualPhoneTransferDto = service.findById(id);
        assertThat(actualPhoneTransferDto).isEqualTo(phoneTransferDto);
        verify(repository).findById(id);
        verify(mapper).toDto(phoneTransferEntity);
    }

    @Test
    @DisplayName("Чтение по не существующему id, негативный сценарий")
    void findByNonExistIdNegativeNest() {
        long id = 3l;
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(returner.getEntityNotFoundException(any()))
                .thenThrow(new EntityNotFoundException("SuspiciousPhoneTransfer по данному id не существует"));
        assertThrows(EntityNotFoundException.class, () -> service.findById(id));
        verify(repository).findById(id);
        verify(returner).getEntityNotFoundException(any());
        verifyNoInteractions(mapper);
    }

    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        long id = 3l;
        System.out.println(id);
        SuspiciousPhoneTransferDto phoneTransferDto = getSuspiciousPhoneTransferDto(id);
        SuspiciousPhoneTransferEntity phoneTransferEntity = getSuspiciousPhoneTransferEntity(id);

        doReturn(Optional.of(phoneTransferEntity)).when(repository).findById(id);
        doReturn(phoneTransferEntity).when(mapper).mergeToEntity(phoneTransferDto, phoneTransferEntity);
        doReturn(phoneTransferEntity).when(repository).save(phoneTransferEntity);
        doReturn(phoneTransferDto).when(mapper).toDto(phoneTransferEntity);

        SuspiciousPhoneTransferDto actualPhoneTransferDto = service.update(id, phoneTransferDto);

        assertThat(actualPhoneTransferDto).isEqualTo(phoneTransferDto);
        verify(mapper).mergeToEntity(phoneTransferDto, phoneTransferEntity);
        verify(repository).save(phoneTransferEntity);
        verify(mapper).toDto(phoneTransferEntity);
    }

    @Test
    @DisplayName("Обновление по не существующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        long id = 3l;
        SuspiciousPhoneTransferDto phoneTransferDto = getSuspiciousPhoneTransferDto(id);
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(returner.getEntityNotFoundException(any()))
                .thenThrow(new EntityNotFoundException("SuspiciousPhoneTransfer по данному id не существует"));

        assertThrows(EntityNotFoundException.class, () -> service.update(id, phoneTransferDto));
        verify(repository).findById(id);
        verify(returner).getEntityNotFoundException(any());
        verifyNoInteractions(mapper);
    }

    @Test
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void findAllByIdsPositiveTest() {
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L, 3L));
        List<SuspiciousPhoneTransferDto> expectedListDto = new ArrayList<>();
        expectedListDto.add(new SuspiciousPhoneTransferDto(1L, 345L, false,
                false, "dont block", "dont suspicious"));
        expectedListDto.add(new SuspiciousPhoneTransferDto(2L, 231L, true,
                false, "dont block", "dont suspicious"));
        expectedListDto.add(new SuspiciousPhoneTransferDto(3L, 111L, false,
                true, "dont block", "dont suspicious"));

        List<SuspiciousPhoneTransferEntity> listEntity = new ArrayList<>();
        listEntity.add(new SuspiciousPhoneTransferEntity(1L, 345L, false,
                false, "dont block", "dont suspicious"));
        listEntity.add(new SuspiciousPhoneTransferEntity(2L, 231L, true,
                false, "dont block", "dont suspicious"));
        listEntity.add(new SuspiciousPhoneTransferEntity(3L, 111L, false,
                true, "dont block", "dont suspicious"));

        IntStream.range(0, ids.size())
                .forEach(i -> doReturn(Optional.of(listEntity.get(i))).when(repository).findById(ids.get(i)));
        doReturn(expectedListDto).when(mapper).toListDto(listEntity);

        List<SuspiciousPhoneTransferDto> actualListDto = service.findAllById(ids);

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
                .thenThrow(new EntityNotFoundException("SuspiciousPhoneTransfer по данному id не существует"));
        assertThrows(EntityNotFoundException.class, () -> service.findAllById(ids));
        verify(repository).findById(ids.get(0));
        verifyNoInteractions(mapper);
    }


    static SuspiciousPhoneTransferDto getSuspiciousPhoneTransferDto(long id) {
        return new SuspiciousPhoneTransferDto(
                id,
                222L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );
    }

    static SuspiciousPhoneTransferEntity getSuspiciousPhoneTransferEntity(long id) {
        return new SuspiciousPhoneTransferEntity(
                id,
                111L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );
    }
}