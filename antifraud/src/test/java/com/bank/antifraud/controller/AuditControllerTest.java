package com.bank.antifraud.controller;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.service.AuditService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты на аудит-контроллер")
class AuditControllerTest {
    @Mock
    private AuditService auditService;
    @InjectMocks
    private AuditController auditController;

    @Test
    @DisplayName("Чтение по id, позитивный сценарий")
    void readByIdPositiveTest() {
        long id = ArgumentMatchers.anyLong();
        AuditDto expectedAuditDto = new AuditDto(
                id,
                "randomEntity",
                "randomType",
                "Ivan",
                "Alex",
                Timestamp.valueOf("2023-08-11 00:00:00"),
                Timestamp.valueOf("2023-08-11 00:00:00"),
                "random Json",
                "random entityJson"
        );

        when(auditService.findById(id)).thenReturn(expectedAuditDto);
        AuditDto actualAuditDto = auditController.read(id);
        assertEquals(expectedAuditDto, actualAuditDto);
        verify(auditService).findById(id);
    }

    @Test
    @DisplayName("Чтение по несуществующему id, негативный сценарий")
    void readByNonExistIdNegativeTest() {
        long id = ArgumentMatchers.anyLong();
        when(auditService.findById(id)).thenThrow(new EntityNotFoundException("Не найден аудит с ID  " + id));
        assertThrows(EntityNotFoundException.class, () -> auditController.read(id));
        verify(auditService).findById(id);
    }
}