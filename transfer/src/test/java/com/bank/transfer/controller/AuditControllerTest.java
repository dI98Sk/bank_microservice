package com.bank.transfer.controller;

import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.service.AuditService;
import com.bank.transfer.service.Impl.AuditServiceImpl;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class AuditControllerTest {

    @InjectMocks
    private AuditController controller;
    @Mock
    private AuditService service;


    @Test
    @DisplayName("Чтение по id, позитивный сценарий")
    void readByIdPositiveTest(){
        AuditDto expectedAuditDto = getAuditDto();
        Mockito.when(service.findById(ArgumentMatchers.anyLong())).thenReturn(expectedAuditDto);
        AuditDto actualAuditDto = controller.read(ArgumentMatchers.anyLong());
        Assertions.assertEquals(expectedAuditDto, actualAuditDto);
    }


    @Test
    @DisplayName("Чтение по несуществующему id, негативный сценарий")
    void readByNonExistIdNegativeTest(){
        Mockito.when(service.findById(ArgumentMatchers.anyLong())).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(EntityNotFoundException.class, () -> controller.read(ArgumentMatchers.anyLong()));
    }



    private AuditDto getAuditDto() {
        return new AuditDto(
                1L,
                "entityType",
                "operationType",
                "createdBy",
                "modifiedBy",
                Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()),
                "newEntityJson",
                "entityJson"
        );
    }
}
