package com.bank.account.mapper;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-23T19:03:25+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class AccountDetailsMapperImpl implements AccountDetailsMapper {

    @Override
    public AccountDetailsEntity toEntity(AccountDetailsDto accountDetails) {
        if ( accountDetails == null ) {
            return null;
        }

        AccountDetailsEntity accountDetailsEntity = new AccountDetailsEntity();

        accountDetailsEntity.setPassportId( accountDetails.getPassportId() );
        accountDetailsEntity.setAccountNumber( accountDetails.getAccountNumber() );
        accountDetailsEntity.setBankDetailsId( accountDetails.getBankDetailsId() );
        accountDetailsEntity.setMoney( accountDetails.getMoney() );
        accountDetailsEntity.setNegativeBalance( accountDetails.getNegativeBalance() );
        accountDetailsEntity.setProfileId( accountDetails.getProfileId() );

        return accountDetailsEntity;
    }

    @Override
    public AccountDetailsDto toDto(AccountDetailsEntity accountDetails) {
        if ( accountDetails == null ) {
            return null;
        }

        AccountDetailsDto accountDetailsDto = new AccountDetailsDto();

        accountDetailsDto.setId( accountDetails.getId() );
        accountDetailsDto.setPassportId( accountDetails.getPassportId() );
        accountDetailsDto.setAccountNumber( accountDetails.getAccountNumber() );
        accountDetailsDto.setBankDetailsId( accountDetails.getBankDetailsId() );
        accountDetailsDto.setMoney( accountDetails.getMoney() );
        accountDetailsDto.setNegativeBalance( accountDetails.getNegativeBalance() );
        accountDetailsDto.setProfileId( accountDetails.getProfileId() );

        return accountDetailsDto;
    }

    @Override
    public List<AccountDetailsDto> toDtoList(List<AccountDetailsEntity> accountDetailsList) {
        if ( accountDetailsList == null ) {
            return null;
        }

        List<AccountDetailsDto> list = new ArrayList<AccountDetailsDto>( accountDetailsList.size() );
        for ( AccountDetailsEntity accountDetailsEntity : accountDetailsList ) {
            list.add( toDto( accountDetailsEntity ) );
        }

        return list;
    }

    @Override
    public AccountDetailsEntity mergeToEntity(AccountDetailsEntity accountDetails, AccountDetailsDto accountDetailsDto) {
        if ( accountDetailsDto == null ) {
            return accountDetails;
        }

        accountDetails.setPassportId( accountDetailsDto.getPassportId() );
        accountDetails.setAccountNumber( accountDetailsDto.getAccountNumber() );
        accountDetails.setBankDetailsId( accountDetailsDto.getBankDetailsId() );
        accountDetails.setMoney( accountDetailsDto.getMoney() );
        accountDetails.setNegativeBalance( accountDetailsDto.getNegativeBalance() );
        accountDetails.setProfileId( accountDetailsDto.getProfileId() );

        return accountDetails;
    }
}
