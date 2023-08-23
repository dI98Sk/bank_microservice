package com.bank.antifraud.mappers;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.entity.AuditEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Тесты на маппер аудита")
class AuditMapperTest {
    private final AuditMapper mapper = new AuditMapperImpl();

    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest() {
        AuditEntity auditEntity = new AuditEntity(
                1L,
                "randomEntity",
                "randomType",
                "Ivan",
                "Alex",
                Timestamp.valueOf("2023-08-11 00:00:00"),
                Timestamp.valueOf("2023-08-11 00:00:00"),
                "random Json",
                "random entityJson"
        );

        AuditDto expectedAuditDto = mapper.toDto(auditEntity);
        AuditDto actualAuditDto = new AuditDto(
                1L,
                "randomEntity",
                "randomType",
                "Ivan",
                "Alex",
                Timestamp.valueOf("2023-08-11 00:00:00"),
                Timestamp.valueOf("2023-08-11 00:00:00"),
                "random Json",
                "random entityJson"
        );

        assertThat(actualAuditDto).isEqualTo(expectedAuditDto);
    }

    @Test
    @DisplayName("Маппинг в дто, на вход подан null")
    void toDtoNullTest() {
        AuditDto auditDto = mapper.toDto(null);
        assertNull(auditDto);
    }
}