package com.bank.transfer.mapper;

import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.entity.AccountTransferEntity;
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
public class AccountTransferMapperImpl implements AccountTransferMapper {

    @Override
    public AccountTransferEntity toEntity(AccountTransferDto transfer) {
        if ( transfer == null ) {
            return null;
        }

        AccountTransferEntity accountTransferEntity = new AccountTransferEntity();

        accountTransferEntity.setAccountNumber( transfer.getAccountNumber() );
        accountTransferEntity.setAmount( transfer.getAmount() );
        accountTransferEntity.setPurpose( transfer.getPurpose() );
        accountTransferEntity.setAccountDetailsId( transfer.getAccountDetailsId() );

        return accountTransferEntity;
    }

    @Override
    public AccountTransferDto toDto(AccountTransferEntity transfer) {
        if ( transfer == null ) {
            return null;
        }

        AccountTransferDto accountTransferDto = new AccountTransferDto();

        accountTransferDto.setId( transfer.getId() );
        accountTransferDto.setAccountNumber( transfer.getAccountNumber() );
        accountTransferDto.setAmount( transfer.getAmount() );
        accountTransferDto.setPurpose( transfer.getPurpose() );
        accountTransferDto.setAccountDetailsId( transfer.getAccountDetailsId() );

        return accountTransferDto;
    }

    @Override
    public AccountTransferEntity mergeToEntity(AccountTransferDto transferDto, AccountTransferEntity transfer) {
        if ( transferDto == null ) {
            return transfer;
        }

        transfer.setAccountNumber( transferDto.getAccountNumber() );
        transfer.setAmount( transferDto.getAmount() );
        transfer.setPurpose( transferDto.getPurpose() );
        transfer.setAccountDetailsId( transferDto.getAccountDetailsId() );

        return transfer;
    }

    @Override
    public List<AccountTransferDto> toDtoList(List<AccountTransferEntity> transfers) {
        if ( transfers == null ) {
            return null;
        }

        List<AccountTransferDto> list = new ArrayList<AccountTransferDto>( transfers.size() );
        for ( AccountTransferEntity accountTransferEntity : transfers ) {
            list.add( toDto( accountTransferEntity ) );
        }

        return list;
    }
}
