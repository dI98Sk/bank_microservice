package com.bank.antifraud.mappers;

import com.bank.antifraud.dto.SuspiciousPhoneTransferDto;
import com.bank.antifraud.entity.SuspiciousPhoneTransferEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Тесты на маппер подозрительных переводов по номеру телефона")
class SuspiciousPhoneTransferMapperTest {
    private final SuspiciousPhoneTransferMapper mapper = new SuspiciousPhoneTransferMapperImpl();

    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest() {
        SuspiciousPhoneTransferEntity suspiciousPhoneTransferEntity = new SuspiciousPhoneTransferEntity(
                1L,
                345L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );

        SuspiciousPhoneTransferDto actualPhoneDto = mapper.toDto(suspiciousPhoneTransferEntity);
        SuspiciousPhoneTransferDto expectedPhoneDto = new SuspiciousPhoneTransferDto(
                1L,
                345L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );

        assertThat(actualPhoneDto).isEqualTo(expectedPhoneDto);
    }

    @Test
    @DisplayName("Маппинг в дто, на вход подан null")
    void toDtoNullTest() {
        SuspiciousPhoneTransferDto suspiciousPhoneTransferDto = mapper.toDto(null);
        assertNull(suspiciousPhoneTransferDto);
    }

    @Test
    @DisplayName("Маппинг в энтити")
    void toEntityTest() {
        SuspiciousPhoneTransferDto suspiciousPhoneTransferDto = new SuspiciousPhoneTransferDto(
                1L,
                345L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );

        SuspiciousPhoneTransferEntity actualPhoneEntity = mapper.toEntity(suspiciousPhoneTransferDto);
        SuspiciousPhoneTransferEntity expectedPhoneEntity = new SuspiciousPhoneTransferEntity(
                null,
                345L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );

        assertThat(actualPhoneEntity).isEqualTo(expectedPhoneEntity);
    }

    @Test
    @DisplayName("Маппинг в энтити, на вход подан null")
    void toEntityNullTest() {
        SuspiciousPhoneTransferEntity suspiciousPhoneTransferEntity = mapper.toEntity(null);
        assertNull(suspiciousPhoneTransferEntity);
    }

    @Test
    @DisplayName("Маппинг в дто")
    void toListDtoTest() {
        List<SuspiciousPhoneTransferEntity> entityList = new ArrayList<>();
        entityList.add(new SuspiciousPhoneTransferEntity(1L, 345L, false,
                false, "dont block", "dont suspicious"));
        entityList.add(new SuspiciousPhoneTransferEntity(2L, 231L, true,
                false, "dont block", "dont suspicious"));
        entityList.add(new SuspiciousPhoneTransferEntity(3L, 111L, false,
                true, "dont block", "dont suspicious"));

        List<SuspiciousPhoneTransferDto> actualListDto = mapper.toListDto(entityList);

        List<SuspiciousPhoneTransferDto> expectedDtoList = new ArrayList<>();
        expectedDtoList.add(new SuspiciousPhoneTransferDto(1L, 345L, false,
                false, "dont block", "dont suspicious"));
        expectedDtoList.add(new SuspiciousPhoneTransferDto(2L, 231L, true,
                false, "dont block", "dont suspicious"));
        expectedDtoList.add(new SuspiciousPhoneTransferDto(3L, 111L, false,
                true, "dont block", "dont suspicious"));

        assertThat(actualListDto).isEqualTo(expectedDtoList);
    }

    @Test
    @DisplayName("Маппинг в дто, на вход подан null")
    void toListDtoNullTest() {
        List<SuspiciousPhoneTransferDto> dtoList = mapper.toListDto(null);
        assertNull(dtoList);
    }

    @Test
    @DisplayName("Слияние в энтити")
    void mergeToEntityTest() {
        SuspiciousPhoneTransferDto suspiciousPhoneTransferDto = new SuspiciousPhoneTransferDto(
                1L,
                345L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );

        SuspiciousPhoneTransferEntity expectedEntity = new SuspiciousPhoneTransferEntity();
        expectedEntity.setPhoneTransferId(suspiciousPhoneTransferDto.getPhoneTransferId());
        expectedEntity.setIsBlocked(suspiciousPhoneTransferDto.getIsBlocked());
        expectedEntity.setIsSuspicious(suspiciousPhoneTransferDto.getIsSuspicious());
        expectedEntity.setBlockedReason(suspiciousPhoneTransferDto.getBlockedReason());
        expectedEntity.setSuspiciousReason(suspiciousPhoneTransferDto.getSuspiciousReason());

        SuspiciousPhoneTransferEntity actualEntity = new SuspiciousPhoneTransferEntity();
        actualEntity = mapper.mergeToEntity(suspiciousPhoneTransferDto, actualEntity);

        assertThat(actualEntity).isEqualTo(expectedEntity);
    }

    @Test
    @DisplayName("Слияние в энтити, на вход подан null")
    void mergeToEntityNullTest() {
        List<SuspiciousPhoneTransferDto> dtoList = mapper.toListDto(null);
        assertNull(dtoList);
    }
}