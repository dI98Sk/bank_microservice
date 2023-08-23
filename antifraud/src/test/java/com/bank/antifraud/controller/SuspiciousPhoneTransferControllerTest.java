package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousPhoneTransferDto;
import com.bank.antifraud.service.SuspiciousPhoneTransferService;
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
@DisplayName("Тесты на контроллер подозрительных переводов по номеру телефона")
class SuspiciousPhoneTransferControllerTest {
    @Mock
    private SuspiciousPhoneTransferService service;
    @InjectMocks
    private SuspiciousPhoneTransferController suspiciousPhoneTransferController;

    @Test
    @DisplayName("Чтение по id, позитивный сценарий")
    void readByIdPositiveTest() {
        long id = 3L;
        SuspiciousPhoneTransferDto SuspiciousPhoneTransferDto = getSuspiciousPhoneTransferDto(id);

        when(service.findById(id)).thenReturn(SuspiciousPhoneTransferDto);
        ResponseEntity<SuspiciousPhoneTransferDto> responseEntityDto = suspiciousPhoneTransferController.read(id);
        assertEquals(responseEntityDto.getBody(), SuspiciousPhoneTransferDto);
        assertEquals(responseEntityDto.getStatusCode(), HttpStatus.OK);
        verify(service).findById(id);
    }

    @Test
    @DisplayName("Чтение по id, негативный сценарий")
    void readByNonExistIdNegativeTest() {
        long id = 3L;

        doThrow(new EntityNotFoundException("SuspiciousPhoneTransfer по данному id не существует"))
                .when(service).findById(id);
        assertThrows(EntityNotFoundException.class, () -> suspiciousPhoneTransferController.read(id));
        verify(service).findById(id);
    }

    @Test
    @DisplayName("Чтение по группе id, позитивный сценарий")
    void readAllByIdsPositiveTest() {
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L, 3L));
        List<SuspiciousPhoneTransferDto> expectedListDto = new ArrayList<>();
        expectedListDto.add(new SuspiciousPhoneTransferDto(1L, 345L, false,
                false, "dont block", "dont suspicious"));
        expectedListDto.add(new SuspiciousPhoneTransferDto(2L, 231L, true,
                false, "dont block", "dont suspicious"));
        expectedListDto.add(new SuspiciousPhoneTransferDto(3L, 111L, false,
                true, "dont block", "dont suspicious"));

        when(service.findAllById(ids)).thenReturn(expectedListDto);
        ResponseEntity<List<SuspiciousPhoneTransferDto>> responseEntityActualListDto =
                suspiciousPhoneTransferController.readAll(ids);
        assertEquals(responseEntityActualListDto.getBody(), expectedListDto);
        assertEquals(responseEntityActualListDto.getStatusCode(), HttpStatus.OK);
        verify(service).findAllById(ids);
    }

    @Test
    @DisplayName("Чтение по не существующему id в группе ids, негативный сценарий")
    void readAllByNonExistIdsNegativeTest() {
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L, 3L));

        doThrow(new EntityNotFoundException("SuspiciousPhoneTransfer по данному id не существует"))
                .when(service).findAllById(ids);
        assertThrows(EntityNotFoundException.class, () -> suspiciousPhoneTransferController.readAll(ids));
        verify(service).findAllById(ids);
    }

    @Test
    @DisplayName("Создание, позитивный сценарий")
    void createPositiveTest() {
        long id = 4L;
        SuspiciousPhoneTransferDto expectedDto = getSuspiciousPhoneTransferDto(id);

        when(service.save(expectedDto)).thenReturn(expectedDto);
        ResponseEntity<SuspiciousPhoneTransferDto> responseEntityDto =
                suspiciousPhoneTransferController.create(expectedDto);
        assertEquals(responseEntityDto.getBody(), expectedDto);
        assertEquals(responseEntityDto.getStatusCode(), HttpStatus.OK);
        verify(service).save(expectedDto);
    }

    @Test
    @DisplayName("Создание записи с существующим phoneTransferId, негативный сценарий")
    void createByExistingIdNegativeTest() {
        long id = 4L;
        SuspiciousPhoneTransferDto actualDto = new SuspiciousPhoneTransferDto(
                1L,
                221L,
                true,
                true,
                "dont block",
                "dont suspicious"
        );

        SuspiciousPhoneTransferDto expectedDto = getSuspiciousPhoneTransferDto(id);
        doThrow(new IllegalArgumentException("Test")).when(service).save(expectedDto);
        assertThrows(IllegalArgumentException.class, () -> suspiciousPhoneTransferController.create(expectedDto));
        assertEquals(actualDto.getPhoneTransferId(), expectedDto.getPhoneTransferId());
        verify(service).save(expectedDto);
    }

    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        long id = 5L;
        SuspiciousPhoneTransferDto expectedDto = getSuspiciousPhoneTransferDto(id);

        when(service.update(id, expectedDto)).thenReturn(expectedDto);
        ResponseEntity<SuspiciousPhoneTransferDto> responseEntityDto =
                suspiciousPhoneTransferController.update(expectedDto, id);
        assertEquals(responseEntityDto.getBody(), expectedDto);
        assertEquals(responseEntityDto.getStatusCode(), HttpStatus.OK);
        verify(service).update(id, expectedDto);
    }

    @Test
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        long id = 4L;
        SuspiciousPhoneTransferDto expectedDto = getSuspiciousPhoneTransferDto(id);

        doThrow(new EntityNotFoundException("SuspiciousPhoneTransfer по данному id не существует"))
                .when(service).update(id, expectedDto);
        assertThrows(EntityNotFoundException.class, () -> suspiciousPhoneTransferController.update(expectedDto, id));
        verify(service).update(id, expectedDto);
    }

    static SuspiciousPhoneTransferDto getSuspiciousPhoneTransferDto(long id) {
        return new SuspiciousPhoneTransferDto(
                id,
                221L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );
    }
}