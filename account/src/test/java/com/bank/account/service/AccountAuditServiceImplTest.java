package com.bank.account.service;

import com.bank.account.dto.AuditDto;
import com.bank.account.entity.AuditEntity;
import com.bank.account.mapper.AccountAuditMapper;
import com.bank.account.repository.AccountAuditRepository;
import com.bank.account.service.common.ExceptionReturner;
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
class AccountAuditServiceImplTest {

    @InjectMocks
    private AccountAuditServiceImpl accountAuditService;
    @Mock
    private AccountAuditRepository repository;
    @Mock
    private AccountAuditMapper mapper;
    @Mock
    private ExceptionReturner exceptionReturner;

    @Test
    @DisplayName("Поиск по id, позитивный сценарий")
    void findByIdPositiveTest() {
        Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(getAuditEntity());
        Mockito.when(mapper.toDto(ArgumentMatchers.any(AuditEntity.class))).thenReturn(getAuditDto());
        AuditDto auditDtoActual = accountAuditService.findById(ArgumentMatchers.anyLong());
        AuditDto auditDtoExpected = getAuditDto();
        Assertions.assertEquals(auditDtoExpected, auditDtoActual);
    }

    @Test
    @DisplayName("Поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeTest() {
        Mockito.when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        Mockito.when(exceptionReturner.getEntityNotFoundException(ArgumentMatchers.anyString()))
                .thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> accountAuditService.findById(ArgumentMatchers.anyLong()));
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