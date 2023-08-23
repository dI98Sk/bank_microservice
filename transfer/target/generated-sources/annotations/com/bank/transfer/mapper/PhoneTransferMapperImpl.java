package com.bank.transfer.mapper;

import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.entity.PhoneTransferEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-19T18:23:40+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class PhoneTransferMapperImpl implements PhoneTransferMapper {

    @Override
    public PhoneTransferEntity toEntity(PhoneTransferDto dto) {
        if ( dto == null ) {
            return null;
        }

        PhoneTransferEntity phoneTransferEntity = new PhoneTransferEntity();

        phoneTransferEntity.setPhoneNumber( dto.getPhoneNumber() );
        phoneTransferEntity.setAmount( dto.getAmount() );
        phoneTransferEntity.setPurpose( dto.getPurpose() );
        phoneTransferEntity.setAccountDetailsId( dto.getAccountDetailsId() );

        return phoneTransferEntity;
    }

    @Override
    public PhoneTransferDto toDto(PhoneTransferEntity transfer) {
        if ( transfer == null ) {
            return null;
        }

        PhoneTransferDto phoneTransferDto = new PhoneTransferDto();

        phoneTransferDto.setId( transfer.getId() );
        phoneTransferDto.setPhoneNumber( transfer.getPhoneNumber() );
        phoneTransferDto.setAmount( transfer.getAmount() );
        phoneTransferDto.setPurpose( transfer.getPurpose() );
        phoneTransferDto.setAccountDetailsId( transfer.getAccountDetailsId() );

        return phoneTransferDto;
    }

    @Override
    public PhoneTransferEntity mergeToEntity(PhoneTransferDto transferDto, PhoneTransferEntity transfer) {
        if ( transferDto == null ) {
            return transfer;
        }

        transfer.setPhoneNumber( transferDto.getPhoneNumber() );
        transfer.setAmount( transferDto.getAmount() );
        transfer.setPurpose( transferDto.getPurpose() );
        transfer.setAccountDetailsId( transferDto.getAccountDetailsId() );

        return transfer;
    }

    @Override
    public List<PhoneTransferDto> toDtoList(List<PhoneTransferEntity> transfers) {
        if ( transfers == null ) {
            return null;
        }

        List<PhoneTransferDto> list = new ArrayList<PhoneTransferDto>( transfers.size() );
        for ( PhoneTransferEntity phoneTransferEntity : transfers ) {
            list.add( toDto( phoneTransferEntity ) );
        }

        return list;
    }
}
