package com.bank.account.mapper;

import com.bank.account.dto.AuditDto;
import com.bank.account.entity.AuditEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountAuditMapperTest {
    private final AccountAuditMapper mapper = new AccountAuditMapperImpl();

    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest() {
        AuditEntity auditEntity = getAuditEntity();
        AuditDto auditDto = mapper.toDto(auditEntity);
        Assertions.assertAll(
                () -> assertEquals(auditEntity.getId(), auditDto.getId()),
                () -> assertEquals(auditEntity.getEntityType(), auditDto.getEntityType()),
                () -> assertEquals(auditEntity.getOperationType(), auditDto.getOperationType()),
                () -> assertEquals(auditEntity.getCreatedBy(), auditDto.getCreatedBy()),
                () -> assertEquals(auditEntity.getModifiedBy(), auditDto.getModifiedBy()),
                () -> assertEquals(auditEntity.getCreatedAt(), auditDto.getCreatedAt()),
                () -> assertEquals(auditEntity.getModifiedAt(), auditDto.getModifiedAt()),
                () -> assertEquals(auditEntity.getNewEntityJson(), auditDto.getNewEntityJson()),
                () -> assertEquals(auditEntity.getEntityJson(), auditDto.getEntityJson())
        );
    }

    @Test
    @DisplayName("Маппинг в дто, на вход подан null")
    void toDtoNullTest() {
        AuditDto auditDto = mapper.toDto(null);
        Assertions.assertNull(auditDto);
    }

    private AuditEntity getAuditEntity() {
        return new AuditEntity(
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