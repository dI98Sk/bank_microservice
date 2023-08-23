package com.bank.antifraud.service.impl;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.entity.AuditEntity;
import com.bank.antifraud.mappers.AuditMapper;
import com.bank.antifraud.repository.AuditRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты на сервис аудита")
class AuditServiceImplTest {
    @Mock
    private AuditRepository repository;
    @Mock
    private AuditMapper mapper;
    @InjectMocks
    private AuditServiceImpl auditService;

    @Test
    @DisplayName("Поиск по id, позитивный сценарий")
    void findByIdPositiveTest() {
        long id = 3L;
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

        AuditEntity expectedAuditEntity = new AuditEntity(
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

        doReturn(Optional.of(expectedAuditEntity)).when(repository).findById(id);
        doReturn(expectedAuditDto).when(mapper).toDto(expectedAuditEntity);
        AuditDto actualResult = auditService.findById(id);
        assertThat(expectedAuditDto).isEqualTo(actualResult);
        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeNest() {
        long id = 3L;
        when(repository.findById(id)).thenThrow(new EntityNotFoundException("Не найден аудит с ID  " + id));
        assertThrows(EntityNotFoundException.class, () -> auditService.findById(id));
        verify(repository).findById(id);
        verifyNoInteractions(mapper);
    }
}