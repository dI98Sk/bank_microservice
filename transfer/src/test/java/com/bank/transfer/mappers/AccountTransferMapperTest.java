package com.bank.transfer.mappers;

import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.entity.AccountTransferEntity;
import com.bank.transfer.mapper.AccountTransferMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AccountTransferMapperTest {
    /*private final AccountTransferMapper mapper = new AccountTransferMapperImpl();

    @Test
    @DisplayName("Маппинг в ентити")
    void toEntityTest() {
        AccountTransferDto accountTransferDto = getAccountTransferDto();
        AccountTransferEntity accountTransferEntity = mapper.toEntity(accountTransferDto);

        Assertions.assertAll(
                () -> assertEquals(accountTransferDto.getAccountNumber(),
                        accountTransferEntity.getAccountNumber()),
                () -> assertEquals(accountTransferDto.getAmount(), accountTransferEntity.getAmount()),
                () -> assertEquals(accountTransferDto.getPurpose(), accountTransferEntity.getPurpose()),
                () -> assertEquals(accountTransferDto.getAccountDetailsId(),
                        accountTransferEntity.getAccountDetailsId())
        );
    }

    @Test
    @DisplayName("Маппинг в ентити, на вход подан null")
    void toEntityNullTest(){
        AccountTransferEntity accountTransferEntity = mapper.toEntity(null);
        Assertions.assertNull(accountTransferEntity);
    }

    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest(){
        AccountTransferEntity accountTransfer = getAccountTransferEntity();
        AccountTransferDto accountTransferDto = mapper.toDto(accountTransfer);

        Assertions.assertAll(
                () -> assertEquals(accountTransfer.getId(), accountTransferDto.getId()),
                () -> assertEquals(accountTransfer.getAccountNumber(),
                        accountTransferDto.getAccountNumber()),
                () -> assertEquals(accountTransfer.getAmount(), accountTransferDto.getAmount()),
                () -> assertEquals(accountTransfer.getPurpose(), accountTransferDto.getPurpose()),
                () -> assertEquals(accountTransfer.getAccountDetailsId(),
                        accountTransferDto.getAccountDetailsId())
        );
    }

    @Test
    @DisplayName("Маппинг в дто, на вход подан null")
    void toDtoNullTest(){
        AccountTransferDto accountTransferDto = mapper.toDto(null);
        Assertions.assertNull(accountTransferDto);
    }

    @Test
    @DisplayName("Слияние в энтити")
    void mergeToEntityTest(){

        AccountTransferDto accountTransferDto = getAccountTransferDto();
        AccountTransferEntity accountTransferEntity = mapper.toEntity(accountTransferDto);
        Assertions.assertAll(
                () -> assertEquals(accountTransferDto.getId(), accountTransferEntity.getId()),
                () -> assertEquals(accountTransferDto.getAccountNumber(),
                        accountTransferEntity.getAccountNumber()),
                () -> assertEquals(accountTransferDto.getAmount(), accountTransferEntity.getAmount()),
                () -> assertEquals(accountTransferDto.getPurpose(), accountTransferEntity.getPurpose()),
                () -> assertEquals(accountTransferDto.getAccountDetailsId(),
                        accountTransferEntity.getAccountDetailsId())
        );
    }

    @Test
    @DisplayName("Слияние в энтити, на вход подан null")
    void mergeToEntityNullTest(){
        AccountTransferEntity accountTransferEntity = mapper.toEntity(null);
        Assertions.assertNull(accountTransferEntity);
    }


    @Test
    @DisplayName("Маппинг в список дто")
    void toDtoListTest(){
        List<AccountTransferDto> listDtoActual = mapper.toDtoList(List.of(getAccountTransferEntity()));
        List<AccountTransferDto> listDtoExpected = List.of(getAccountTransferDto());

        Assertions.assertEquals(listDtoExpected, listDtoActual);
    }

    @Test
    @DisplayName("Маппинг в список дто, на вход подан null")
    void toDtoListNullTest(){
        List<AccountTransferDto> listDto = mapper.toDtoList(null);
        Assertions.assertNull(listDto);
    }

    private AccountTransferEntity getAccountTransferEntity(){
        return new AccountTransferEntity(
               1L,
               2L,
               BigDecimal.TEN,
               "purpose",
               3L
        );
    }

    private AccountTransferDto getAccountTransferDto() {
        return new AccountTransferDto(
                1L,
                2L,
                BigDecimal.TEN,
                "purpose",
                3L
        );
    }*/
}
