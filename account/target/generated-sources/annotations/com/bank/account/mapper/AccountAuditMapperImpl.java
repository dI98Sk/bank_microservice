package com.bank.account.mapper;

import com.bank.account.dto.AuditDto;
import com.bank.account.entity.AuditEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-23T19:03:25+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class AccountAuditMapperImpl implements AccountAuditMapper {

    @Override
    public AuditDto toDto(AuditEntity audit) {
        if ( audit == null ) {
            return null;
        }

        AuditDto auditDto = new AuditDto();

        auditDto.setId( audit.getId() );
        auditDto.setEntityType( audit.getEntityType() );
        auditDto.setOperationType( audit.getOperationType() );
        auditDto.setCreatedBy( audit.getCreatedBy() );
        auditDto.setModifiedBy( audit.getModifiedBy() );
        auditDto.setCreatedAt( audit.getCreatedAt() );
        auditDto.setModifiedAt( audit.getModifiedAt() );
        auditDto.setNewEntityJson( audit.getNewEntityJson() );
        auditDto.setEntityJson( audit.getEntityJson() );

        return auditDto;
    }
}
