package com.bank.transfer.mappers;

import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.AuditEntity;
import com.bank.transfer.mapper.AuditMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuditMapperTest {
    /*private final AuditMapper mapper = new AuditMapperImpl();

    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest(){
        AuditEntity auditEntity = getAuditEntity();
        AuditDto auditDto = mapper.toDto(auditEntity);
        Assertions.assertAll(
                () -> assertEquals(auditEntity.getId(), auditDto.getId()),
                () -> assertEquals(auditEntity.getEntityType(), auditDto.getEntityType()),
                () -> assertEquals(auditEntity.getOperationType(), auditDto.getOperationType()),
                () -> Assertions.assertEquals(auditEntity.getCreatedBy(), auditDto.getCreatedBy()),
                () -> Assertions.assertEquals(auditEntity.getModifiedBy(), auditDto.getModifiedBy()),
                () -> Assertions.assertEquals(auditEntity.getCreatedAt(), auditDto.getCreatedAt()),
                () -> Assertions.assertEquals(auditEntity.getModifiedAt(), auditDto.getModifiedAt()),
                () -> Assertions.assertEquals(auditEntity.getNewEntityJson(), auditDto.getNewEntityJson()),
                () -> Assertions.assertEquals(auditEntity.getEntityJson(), auditDto.getEntityJson())
        );
    }

    @Test
    @DisplayName("Маппинг в дто, на вход подан null")
    void toDtoNullTest(){
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
    }*/
}
