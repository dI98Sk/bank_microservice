package com.bank.transfer.mapper;

import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.entity.CardTransferEntity;
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
public class CardTransferMapperImpl implements CardTransferMapper {

    @Override
    public CardTransferEntity toEntity(CardTransferDto transfer) {
        if ( transfer == null ) {
            return null;
        }

        CardTransferEntity cardTransferEntity = new CardTransferEntity();

        cardTransferEntity.setCardNumber( transfer.getCardNumber() );
        cardTransferEntity.setAmount( transfer.getAmount() );
        cardTransferEntity.setPurpose( transfer.getPurpose() );
        cardTransferEntity.setAccountDetailsId( transfer.getAccountDetailsId() );

        return cardTransferEntity;
    }

    @Override
    public CardTransferDto toDto(CardTransferEntity transfer) {
        if ( transfer == null ) {
            return null;
        }

        CardTransferDto cardTransferDto = new CardTransferDto();

        cardTransferDto.setId( transfer.getId() );
        cardTransferDto.setCardNumber( transfer.getCardNumber() );
        cardTransferDto.setAmount( transfer.getAmount() );
        cardTransferDto.setPurpose( transfer.getPurpose() );
        cardTransferDto.setAccountDetailsId( transfer.getAccountDetailsId() );

        return cardTransferDto;
    }

    @Override
    public CardTransferEntity mergeToEntity(CardTransferDto transferDto, CardTransferEntity transfer) {
        if ( transferDto == null ) {
            return transfer;
        }

        transfer.setCardNumber( transferDto.getCardNumber() );
        transfer.setAmount( transferDto.getAmount() );
        transfer.setPurpose( transferDto.getPurpose() );
        transfer.setAccountDetailsId( transferDto.getAccountDetailsId() );

        return transfer;
    }

    @Override
    public List<CardTransferDto> toDtoList(List<CardTransferEntity> transfers) {
        if ( transfers == null ) {
            return null;
        }

        List<CardTransferDto> list = new ArrayList<CardTransferDto>( transfers.size() );
        for ( CardTransferEntity cardTransferEntity : transfers ) {
            list.add( toDto( cardTransferEntity ) );
        }

        return list;
    }
}
