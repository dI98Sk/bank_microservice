package com.bank.antifraud.dto;

import com.bank.antifraud.entity.AuditEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Dto для {@link AuditEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuditDto implements Serializable {

    Long id;
    String entityType;
    String operationType;
    String createdBy;
    String modifiedBy;
    Timestamp createdAt;
    Timestamp modifiedAt;
    String newEntityJson;
    String entityJson;
}
