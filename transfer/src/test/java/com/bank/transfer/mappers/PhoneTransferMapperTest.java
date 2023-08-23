package com.bank.transfer.mappers;

import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.entity.PhoneTransferEntity;
import com.bank.transfer.mapper.PhoneTransferMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;


public class PhoneTransferMapperTest {

    /*private final PhoneTransferMapper mapper = new CardTransferMapperImpl();

    @Test
    @DisplayName("Маппинг в ентити")
    void toEntityTest(){
        PhoneTransferDto phoneTransferDto = getPhoneTransferDto();
        PhoneTransferEntity phoneTransferEntity = mapper.toEntity(phoneTransferDto);

        Assertions.assertAll(
                () -> Assertions.assertEquals(phoneTransferDto.getId(), phoneTransferEntity.getId()),
                () -> Assertions.assertEquals(phoneTransferDto.getPhoneNumber(),
                        phoneTransferEntity.getPhoneNumber()),
                () -> Assertions.assertEquals(phoneTransferDto.getAmount(), phoneTransferEntity.getAmount()),
                () -> Assertions.assertEquals(phoneTransferDto.getPurpose(), phoneTransferEntity.getPurpose()),
                () -> Assertions.assertEquals(phoneTransferDto.getAccountDetailsId(),
                        phoneTransferEntity.getAccountDetailsId())
        );
    }

    @Test
    @DisplayName("Маппинг в ентити, на вход подан null")
    void toEntityNullTest(){
        PhoneTransferEntity phoneTransfer = mapper.toEntity(null);
        Assertions.assertNull(phoneTransfer);
    }

    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest(){
        PhoneTransferEntity phoneTransferEntity = getPhoneTransferEntity();
        PhoneTransferDto phoneTransferDto = mapper.toDto(phoneTransferEntity);

        Assertions.assertAll(
                () -> Assertions.assertEquals(phoneTransferEntity.getId(), phoneTransferDto.getId()),
                () -> Assertions.assertEquals(phoneTransferEntity.getPhoneNumber(),
                        phoneTransferDto.getPhoneNumber()),
                () -> Assertions.assertEquals(phoneTransferEntity.getAmount(), phoneTransferDto.getAmount()),
                () -> Assertions.assertEquals(phoneTransferEntity.getPurpose(), phoneTransferDto.getPurpose()),
                () -> Assertions.assertEquals(phoneTransferEntity.getAccountDetailsId(),
                        phoneTransferDto.getAccountDetailsId())
        );
    }

    @Test
    @DisplayName("Маппинг в дто, на вход подан null")
    void toDtoNullTest(){
        PhoneTransferDto phoneTransferDto = mapper.toDto(null);
        Assertions.assertNull(phoneTransferDto);
    }

    @Test
    @DisplayName("Слияние в энтити")
    void mergeToEntityTest(){
        PhoneTransferDto phoneTransferDto = getPhoneTransferDto();
        PhoneTransferEntity phoneTransferEntity = mapper.toEntity(phoneTransferDto);

        Assertions.assertAll(
                () -> Assertions.assertEquals(phoneTransferDto.getId(), phoneTransferEntity.getId()),
                () -> Assertions.assertEquals(phoneTransferDto.getPhoneNumber(),
                        phoneTransferEntity.getPhoneNumber()),
                () -> Assertions.assertEquals(phoneTransferDto.getAmount(), phoneTransferEntity.getAmount()),
                () -> Assertions.assertEquals(phoneTransferDto.getPurpose(), phoneTransferEntity.getPurpose()),
                () -> Assertions.assertEquals(phoneTransferDto.getAccountDetailsId(),
                        phoneTransferEntity.getAccountDetailsId())
        );
    }

    @Test
    @DisplayName("Слияние в энтити, на вход подан null")
    void mergeToEntityNullTest(){
        PhoneTransferEntity phoneTransferEntity = mapper.toEntity(null);
        Assertions.assertNull(phoneTransferEntity);
    }

    @Test
    @DisplayName("Маппинг в список дто")
    void toDtoListTest(){
        List<PhoneTransferDto> phoneTransferDtoActual = mapper.toDtoList(List.of(getPhoneTransferEntity()));
        List<PhoneTransferDto> phoneTransferDtoExpected = List.of(getPhoneTransferDto());

        Assertions.assertEquals(phoneTransferDtoExpected, phoneTransferDtoActual);
    }

    @Test
    @DisplayName("Маппинг в список дто, на вход подан null")
    void toDtoListNullTest(){
        List<PhoneTransferDto> phoneTransferDto = mapper.toDtoList(null);
        Assertions.assertNull(phoneTransferDto);
    }

    private PhoneTransferEntity getPhoneTransferEntity() {
        return new PhoneTransferEntity(
                1L,
                2L,
                BigDecimal.TEN,
                "purpose",
                3L
        );
    }

    private PhoneTransferDto getPhoneTransferDto() {
        return new PhoneTransferDto(
                1L,
                2L,
                BigDecimal.TEN,
                "purpose",
                3L
        );
    }
*/

}
