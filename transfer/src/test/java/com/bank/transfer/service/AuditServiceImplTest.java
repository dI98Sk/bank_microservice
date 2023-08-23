package com.bank.transfer.service;

import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.AuditEntity;
import com.bank.transfer.mapper.AuditMapper;
import com.bank.transfer.repository.AuditRepository;
import com.bank.transfer.service.Impl.AuditServiceImpl;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AuditServiceImplTest {

    @InjectMocks
    private AuditServiceImpl auditServiceImpl;
    @Mock
    private AuditRepository repository;
    @Mock
    private AuditMapper mapper;
    @Mock
    private EntityNotFoundReturner notFoundReturner;

    @Test
    @DisplayName("Поиск по id, позитивный сценарий")
    void findByIdPositiveTest(){
        Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(getAuditEntity());
        Mockito.when(mapper.toDto(ArgumentMatchers.any(AuditEntity.class))).thenReturn(getAuditDto());

        AuditDto auditDtoActual = auditServiceImpl.findById(ArgumentMatchers.anyLong());
        AuditDto auditDtoExpected = getAuditDto();

        Assertions.assertEquals(auditDtoExpected, auditDtoActual);

    }

    @Test
    @DisplayName("Поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeTest() {
        Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        Mockito.when(notFoundReturner.getEntityNotFoundException(ArgumentMatchers.anyLong(), ArgumentMatchers.anyString()))
                .thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> auditServiceImpl.findById(ArgumentMatchers.anyLong()));
    }


    private Optional<AuditEntity> getAuditEntity() {
        AuditEntity auditEntity = new AuditEntity(
                1L,
                "entityType",
                "operationType",
                "createdBy",
                "modifiedBy",
                Timestamp.valueOf(LocalDateTime.MIN),
                Timestamp.valueOf(LocalDateTime.MIN),
                "newEntityJson",
                "entityJson"
        );
        return Optional.of(auditEntity);
    }

    private AuditDto getAuditDto() {
        return new AuditDto(
                1L,
                "entityType",
                "operationType",
                "createdBy",
                "modifiedBy",
                Timestamp.valueOf(LocalDateTime.MIN),
                Timestamp.valueOf(LocalDateTime.MIN),
                "newEntityJson",
                "entityJson"
        );
    }


}
