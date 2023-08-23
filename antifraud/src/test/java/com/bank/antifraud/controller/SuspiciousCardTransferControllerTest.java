package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.service.SuspiciousCardTransferService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты на контроллер подозрительных переводов по номеру карты")
class SuspiciousCardTransferControllerTest {
    @Mock
    private SuspiciousCardTransferService service;
    @InjectMocks
    private SuspiciousCardTransferController suspiciousCardTransferController;

    @Test
    @DisplayName("Чтение по id, позитивный сценарий")
    void readByIdPositiveTest() {
        long id = 3L;
        SuspiciousCardTransferDto SuspiciousCardTransferDto = getSuspiciousCardTransferDto(id);

        when(service.findById(id)).thenReturn(SuspiciousCardTransferDto);
        ResponseEntity<SuspiciousCardTransferDto> responseEntityDto = suspiciousCardTransferController.read(id);
        assertEquals(responseEntityDto.getBody(), SuspiciousCardTransferDto);
        assertEquals(responseEntityDto.getStatusCode(), HttpStatus.OK);
        verify(service).findById(id);
    }

    @Test
    @DisplayName("Чтение по несуществующему id, негативный сценарий")
    void readByNonExistIdNegativeTest() {
        long id = 3L;

        doThrow(new EntityNotFoundException("SuspiciousCardTransfer по данному id не существует"))
                .when(service).findById(id);
        assertThrows(EntityNotFoundException.class, () -> suspiciousCardTransferController.read(id));
        verify(service).findById(id);
    }

    @Test
    @DisplayName("Чтение по группе id, позитивный сценарий")
    void readAllByIdsPositiveTest() {
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L, 3L));
        List<SuspiciousCardTransferDto> expectedListDto = new ArrayList<>();
        expectedListDto.add(new SuspiciousCardTransferDto(1L, 345L, false,
                false, "dont block", "dont suspicious"));
        expectedListDto.add(new SuspiciousCardTransferDto(2L, 231L, true,
                false, "dont block", "dont suspicious"));
        expectedListDto.add(new SuspiciousCardTransferDto(3L, 111L, false,
                true, "dont block", "dont suspicious"));

        when(service.findAllById(ids)).thenReturn(expectedListDto);
        ResponseEntity<List<SuspiciousCardTransferDto>> responseEntityActualListDto =
                suspiciousCardTransferController.readAll(ids);
        assertEquals(responseEntityActualListDto.getBody(), expectedListDto);
        assertEquals(responseEntityActualListDto.getStatusCode(), HttpStatus.OK);
        verify(service).findAllById(ids);
    }

    @Test
    @DisplayName("Чтение по не существующему id в группе ids, негативный сценарий")
    void readAllByNonExistIdsNegativeTest() {
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L, 3L));

        doThrow(new EntityNotFoundException("SuspiciousCardTransfer по данному id не существует"))
                .when(service).findAllById(ids);
        assertThrows(EntityNotFoundException.class, () -> suspiciousCardTransferController.readAll(ids));
        verify(service).findAllById(ids);
    }

    @Test
    @DisplayName("Создание, позитивный сценарий")
    void createPositiveTest() {
        long id = 4L;

        SuspiciousCardTransferDto expectedDto = getSuspiciousCardTransferDto(id);

        when(service.save(expectedDto)).thenReturn(expectedDto);
        ResponseEntity<SuspiciousCardTransferDto> responseEntityDto =
                suspiciousCardTransferController.create(expectedDto);
        assertEquals(responseEntityDto.getBody(), expectedDto);
        assertEquals(responseEntityDto.getStatusCode(), HttpStatus.OK);
        verify(service).save(expectedDto);
    }

    @Test
    @DisplayName("Создание записи с существующим cardTransferId, негативный сценарий")
    void createByExistingIdNegativeTest() {
        long id = 4L;
        SuspiciousCardTransferDto actualDto = new SuspiciousCardTransferDto(
                1L,
                221L,
                false,
                true,
                "block",
                "dont suspicious"
        );

        SuspiciousCardTransferDto expectedDto = getSuspiciousCardTransferDto(id);
        doThrow(new IllegalArgumentException("Test")).when(service).save(expectedDto);
        assertThrows(IllegalArgumentException.class, () -> suspiciousCardTransferController.create(expectedDto));
        assertEquals(actualDto.getCardTransferId(), expectedDto.getCardTransferId());
        verify(service).save(expectedDto);
    }

    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        long id = 5L;
        SuspiciousCardTransferDto expectedDto = getSuspiciousCardTransferDto(id);

        when(service.update(id, expectedDto)).thenReturn(expectedDto);
        ResponseEntity<SuspiciousCardTransferDto> responseEntityDto =
                suspiciousCardTransferController.update(expectedDto, id);
        assertEquals(responseEntityDto.getBody(), expectedDto);
        assertEquals(responseEntityDto.getStatusCode(), HttpStatus.OK);
        verify(service).update(id, expectedDto);
    }

    @Test
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        long id = 4L;
        SuspiciousCardTransferDto expectedDto = getSuspiciousCardTransferDto(id);

        doThrow(new EntityNotFoundException("SuspiciousCardTransfer по данному id не существует"))
                .when(service).update(id, expectedDto);
        assertThrows(EntityNotFoundException.class, () -> suspiciousCardTransferController.update(expectedDto, id));
        verify(service).update(id, expectedDto);
    }

    static SuspiciousCardTransferDto getSuspiciousCardTransferDto(long id) {
        return new SuspiciousCardTransferDto(
                id,
                221L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );
    }
}