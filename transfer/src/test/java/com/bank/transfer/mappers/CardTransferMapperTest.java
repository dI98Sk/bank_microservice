package com.bank.transfer.mappers;

import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.entity.CardTransferEntity;
import com.bank.transfer.mapper.CardTransferMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CardTransferMapperTest {

    /*private final CardTransferMapper mapper = CardTransferMapperImpl();

    @Test
    @DisplayName("Маппинг в ентити")
    void toEntityTest(){
        CardTransferDto cardTransferDto = getCardTransferDto();
        CardTransferEntity cardTransferEntity = mapper.toEntity(cardTransferDto);

        assertAll(
                () -> assertEquals(cardTransferDto.getId(),cardTransferEntity.getId()),
                () -> assertEquals(cardTransferDto.getCardNumber(),cardTransferEntity.getCardNumber()),
                () -> assertEquals(cardTransferDto.getAmount(),cardTransferEntity.getAmount()),
                () -> assertEquals(cardTransferDto.getPurpose(),cardTransferEntity.getPurpose()),
                () -> assertEquals(cardTransferDto.getAccountDetailsId(),
                        cardTransferEntity.getAccountDetailsId())
        );
    }

    @Test
    @DisplayName("Маппинг в ентити, на вход подан null")
    void toEntityNullTest(){
        CardTransferEntity cardTransferEntity = mapper.toEntity(null);
        assertNull(cardTransferEntity);
    }

    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest(){
        CardTransferEntity cardTransferEntity = getCardTransferEntity();
        CardTransferDto cardTransferDto = mapper.toDto(getCardTransferEntity());
        assertAll(
                () -> assertEquals(cardTransferEntity.getId(), cardTransferDto.getId()),
                () -> assertEquals(cardTransferEntity.getCardNumber(), cardTransferDto.getCardNumber()),
                () -> assertEquals(cardTransferEntity.getAmount(), cardTransferDto.getAmount()),
                () -> assertEquals(cardTransferEntity.getPurpose(), cardTransferDto.getPurpose()),
                () -> assertEquals(cardTransferEntity.getAccountDetailsId(),
                        cardTransferDto.getAccountDetailsId())
        );
    }

    @Test
    @DisplayName("Маппинг в дто, на вход подан null")
    void toDtoNullTest(){
        CardTransferDto cardTransferDto = mapper.toDto(null);
        Assertions.assertNull(cardTransferDto);
    }


    @Test
    @DisplayName("Слияние в энтити")
    void mergeToEntityTest(){
        CardTransferDto cardTransferDto = getCardTransferDto();
        CardTransferEntity cardTransferEntity = mapper.toEntity(cardTransferDto);

        Assertions.assertAll(
                () -> Assertions.assertEquals(cardTransferDto.getId(), cardTransferEntity.getId()),
                () -> Assertions.assertEquals(cardTransferDto.getCardNumber(),
                        cardTransferEntity.getCardNumber()),
                () -> Assertions.assertEquals(cardTransferDto.getAmount(), cardTransferEntity.getAmount()),
                () -> Assertions.assertEquals(cardTransferDto.getPurpose(), cardTransferEntity.getPurpose()),
                () -> Assertions.assertEquals(cardTransferDto.getAccountDetailsId(),
                        cardTransferEntity.getAccountDetailsId())
        );
    }

    @Test
    @DisplayName("Слияние в энтити, на вход подан null")
    void mergeToEntityNullTest(){
        CardTransferEntity cardTransferEntity = mapper.toEntity(null);
        Assertions.assertNull(cardTransferEntity);
    }

    @Test
    @DisplayName("Маппинг в список дто")
    void toDtoListTest(){
        List<CardTransferDto> cardTransferActual = mapper.toDtoList(List.of(getCardTransferEntity()));
        List<CardTransferDto> cardTransferExpected = List.of(getCardTransferDto());

        Assertions.assertEquals(cardTransferExpected, cardTransferActual);
    }

    @Test
    @DisplayName("Маппинг в список дто, на вход подан null")
    void toDtoListNullTest(){
        List<CardTransferDto> cardTransferDto = mapper.toDtoList(null);
        Assertions.assertNull(cardTransferDto);
    }

    private CardTransferEntity getCardTransferEntity() {
        return new CardTransferEntity(
                1L,
                2L,
                BigDecimal.TEN,
                "purpose",
                3L
        );
    }

    private CardTransferDto getCardTransferDto() {
        return new CardTransferDto(
                1L,
                2L,
                BigDecimal.TEN,
                "purpose",
                3L
        );
    }*/
}
