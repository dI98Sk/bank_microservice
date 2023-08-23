package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousAccountTransferDto;
import com.bank.antifraud.service.SuspiciousAccountTransferService;
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
@DisplayName("Тесты на контроллер подозрительных переводов по номеру счета")
class SuspiciousAccountTransferControllerTest {
    @Mock
    private SuspiciousAccountTransferService service;
    @InjectMocks
    private SuspiciousAccountTransferController suspiciousAccountTransferController;

    @Test
    @DisplayName("Чтение по id, позитивный сценарий")
    void readByIdPositiveTest() {
        long id = 3L;
        SuspiciousAccountTransferDto suspiciousAccountTransferDto = getSuspiciousAccountTransferDto(id);

        when(service.findById(id)).thenReturn(suspiciousAccountTransferDto);
        ResponseEntity<SuspiciousAccountTransferDto> responseEntityDto = suspiciousAccountTransferController.read(id);
        assertEquals(responseEntityDto.getBody(), suspiciousAccountTransferDto);
        assertEquals(responseEntityDto.getStatusCode(), HttpStatus.OK);
        verify(service).findById(id);
    }

    @Test
    @DisplayName("Чтение по не существующему id, негативный сценарий")
    void readByNonExistIdNegativeTest() {
        long id = 3L;

        doThrow(new EntityNotFoundException("SuspiciousAccountTransfer по данному id не существует"))
                .when(service).findById(id);
        assertThrows(EntityNotFoundException.class, () -> suspiciousAccountTransferController.read(id));
        verify(service).findById(id);
    }

    @Test
    @DisplayName("Чтение  по нескольким id, позитивный сценарий")
    void readAllByIdsPositiveTest() {
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L, 3L));
        List<SuspiciousAccountTransferDto> expectedListDto = new ArrayList<>();
        expectedListDto.add(new SuspiciousAccountTransferDto(1L, 345L, false,
                false, "dont block", "dont suspicious"));
        expectedListDto.add(new SuspiciousAccountTransferDto(2L, 231L, true,
                false, "dont block", "dont suspicious"));
        expectedListDto.add(new SuspiciousAccountTransferDto(3L, 111L, false,
                true, "dont block", "dont suspicious"));

        when(service.findAllById(ids)).thenReturn(expectedListDto);
        ResponseEntity<List<SuspiciousAccountTransferDto>> responseEntityActualListDto =
                suspiciousAccountTransferController.readAll(ids);
        assertEquals(responseEntityActualListDto.getBody(), expectedListDto);
        assertEquals(responseEntityActualListDto.getStatusCode(), HttpStatus.OK);
        verify(service).findAllById(ids);
    }

    @Test
    @DisplayName("Чтение  по нескольким несуществующим id, негативный сценарий")
    void readAllByNonExistIdsNegativeTest() {
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L, 3L));

        doThrow(new EntityNotFoundException("SuspiciousAccountTransfer по данному id не существует"))
                .when(service).findAllById(ids);
        assertThrows(EntityNotFoundException.class, () -> suspiciousAccountTransferController.readAll(ids));
        verify(service).findAllById(ids);
    }

    @Test
    @DisplayName("Создание, позитивный сценарий")
    void createPositiveTest() {
        long id = 4L;
        SuspiciousAccountTransferDto expectedDto = getSuspiciousAccountTransferDto(id);

        when(service.save(expectedDto)).thenReturn(expectedDto);
        ResponseEntity<SuspiciousAccountTransferDto> responseEntityDto =
                suspiciousAccountTransferController.create(expectedDto);
        assertEquals(responseEntityDto.getBody(), expectedDto);
        assertEquals(responseEntityDto.getStatusCode(), HttpStatus.OK);
        verify(service).save(expectedDto);
    }

    @Test
    @DisplayName("Создание по существующему id, негативный сценарий")
    void createByExistingIdNegativeTest() {
        long id = 4L;
        SuspiciousAccountTransferDto actualDto = new SuspiciousAccountTransferDto(
                2L,
                345L,
                true,
                false,
                "dont block",
                "dont suspicious"
        );

        SuspiciousAccountTransferDto expectedDto = getSuspiciousAccountTransferDto(id);
        doThrow(new IllegalArgumentException("Test")).when(service).save(expectedDto);
        assertThrows(IllegalArgumentException.class, () -> suspiciousAccountTransferController.create(expectedDto));
        assertEquals(actualDto.getAccountTransferId(), expectedDto.getAccountTransferId());
        verify(service).save(expectedDto);
    }

    @Test
    @DisplayName("ООбновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        long id = 5L;
        SuspiciousAccountTransferDto expectedDto = getSuspiciousAccountTransferDto(id);

        when(service.update(id, expectedDto)).thenReturn(expectedDto);
        ResponseEntity<SuspiciousAccountTransferDto> responseEntityDto =
                suspiciousAccountTransferController.update(expectedDto, id);
        assertEquals(responseEntityDto.getBody(), expectedDto);
        assertEquals(responseEntityDto.getStatusCode(), HttpStatus.OK);
        verify(service).update(id, expectedDto);
    }

    @Test
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        long id = 4L;
        SuspiciousAccountTransferDto expectedDto = getSuspiciousAccountTransferDto(id);

        doThrow(new EntityNotFoundException("SuspiciousAccountTransfer по данному id не существует"))
                .when(service).update(id, expectedDto);
        assertThrows(EntityNotFoundException.class, () -> suspiciousAccountTransferController.update(expectedDto, id));
        verify(service).update(id, expectedDto);

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
}