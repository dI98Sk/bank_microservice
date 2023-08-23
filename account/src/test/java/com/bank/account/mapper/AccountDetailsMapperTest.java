package com.bank.account.mapper;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountDetailsMapperTest {
    private final AccountDetailsMapper mapper = new AccountDetailsMapperImpl();

    @Test
    @DisplayName("Маппинг в ентити")
    void toEntityTest() {
        AccountDetailsDto accountDetailsDto = getAccountDetailsDto();
        AccountDetailsEntity accountDetailsEntity = mapper.toEntity(accountDetailsDto);
        Assertions.assertAll(
                () -> assertEquals(accountDetailsDto.getPassportId(), accountDetailsEntity.getPassportId()),
                () -> assertEquals(accountDetailsDto.getAccountNumber(), accountDetailsEntity.getAccountNumber()),
                () -> assertEquals(accountDetailsDto.getBankDetailsId(), accountDetailsEntity.getBankDetailsId()),
                () -> assertEquals(accountDetailsDto.getMoney(), accountDetailsEntity.getMoney()),
                () -> assertEquals(accountDetailsDto.getNegativeBalance(), accountDetailsEntity.getNegativeBalance()),
                () -> assertEquals(accountDetailsDto.getProfileId(), accountDetailsEntity.getProfileId())
        );
    }

    @Test
    @DisplayName("Маппинг в ентити, на вход подан null")
    void toEntityNullTest() {
        AccountDetailsEntity accountDetailsEntity = mapper.toEntity(null);
        Assertions.assertNull(accountDetailsEntity);
    }

    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest() {
        AccountDetailsEntity accountDetailsEntity = getAccountDetailsEntity();
        AccountDetailsDto accountDetailsDto = mapper.toDto(accountDetailsEntity);
        Assertions.assertAll(
                () -> assertEquals(accountDetailsEntity.getId(), accountDetailsDto.getId()),
                () -> assertEquals(accountDetailsEntity.getPassportId(), accountDetailsDto.getPassportId()),
                () -> assertEquals(accountDetailsEntity.getAccountNumber(), accountDetailsDto.getAccountNumber()),
                () -> assertEquals(accountDetailsEntity.getBankDetailsId(), accountDetailsDto.getBankDetailsId()),
                () -> assertEquals(accountDetailsEntity.getMoney(), accountDetailsDto.getMoney()),
                () -> assertEquals(accountDetailsEntity.getNegativeBalance(), accountDetailsDto.getNegativeBalance()),
                () -> assertEquals(accountDetailsEntity.getProfileId(), accountDetailsDto.getProfileId())
        );
    }

    @Test
    @DisplayName("Маппинг в дто, на вход подан null")
    void toDtoNullTest() {
        AccountDetailsDto accountDetailsDto = mapper.toDto(null);
        Assertions.assertNull(accountDetailsDto);
    }

    @Test
    @DisplayName("Слияние в энтити")
    void mergeToEntityTest() {
        AccountDetailsDto accountDetailsDto = getAccountDetailsDto();
        AccountDetailsEntity accountDetailsEntity = mapper.toEntity(accountDetailsDto);
        Assertions.assertAll(
                () -> assertEquals(accountDetailsDto.getPassportId(), accountDetailsEntity.getPassportId()),
                () -> assertEquals(accountDetailsDto.getAccountNumber(), accountDetailsEntity.getAccountNumber()),
                () -> assertEquals(accountDetailsDto.getBankDetailsId(), accountDetailsEntity.getBankDetailsId()),
                () -> assertEquals(accountDetailsDto.getMoney(), accountDetailsEntity.getMoney()),
                () -> assertEquals(accountDetailsDto.getNegativeBalance(), accountDetailsEntity.getNegativeBalance()),
                () -> assertEquals(accountDetailsDto.getProfileId(), accountDetailsEntity.getProfileId())
        );
    }

    @Test
    @DisplayName("Слияние в энтити, на вход подан null")
    void mergeToEntityNullTest() {
        AccountDetailsEntity accountDetailsEntity = mapper.toEntity(null);
        Assertions.assertNull(accountDetailsEntity);
    }

    @Test
    @DisplayName("Маппинг в список дто")
    void toDtoListTest() {
        List<AccountDetailsDto> listDtoActual = mapper.toDtoList(List.of(getAccountDetailsEntity()));
        List<AccountDetailsDto> listDtoExpected = List.of(getAccountDetailsDto());
        Assertions.assertIterableEquals(listDtoExpected, listDtoActual);
    }

    @Test
    @DisplayName("Маппинг в список дто, на вход подан null")
    void toDtoListNullTest() {
        List<AccountDetailsDto> listDto = mapper.toDtoList(null);
        Assertions.assertNull(listDto);
    }

    private AccountDetailsDto getAccountDetailsDto() {
        return new AccountDetailsDto(
                1L,
                1L,
                1L,
                1L,
                BigDecimal.TEN,
                false,
                1L
        );
    }

    private AccountDetailsEntity getAccountDetailsEntity() {
        return new AccountDetailsEntity(
                1L,
                1L,
                1L,
                1L,
                BigDecimal.TEN,
                false,
                1L
        );
    }
}