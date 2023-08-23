package com.bank.antifraud.mappers;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Тесты на маппер подозрительных переводов по номеру карты")
class SuspiciousCardTransferMapperTest {
    private final SuspiciousCardTransferMapper mapper = new SuspiciousCardTransferMapperImpl();

    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest() {
        SuspiciousCardTransferEntity suspiciousCardTransferEntity = new SuspiciousCardTransferEntity(
                1L,
                345L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );

        SuspiciousCardTransferDto actualCardDto = mapper.toDto(suspiciousCardTransferEntity);
        SuspiciousCardTransferDto expectedCardDto = new SuspiciousCardTransferDto(
                1L,
                345L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );

        assertThat(actualCardDto).isEqualTo(expectedCardDto);
    }

    @Test
    @DisplayName("Маппинг в дто, на вход подан null")
    void toDtoNullTest() {
        SuspiciousCardTransferDto suspiciousCardTransferDto = mapper.toDto(null);
        assertNull(suspiciousCardTransferDto);
    }

    @Test
    @DisplayName("Маппинг в энтити")
    void toEntityTest() {
        SuspiciousCardTransferDto suspiciousCardTransferDto = new SuspiciousCardTransferDto(
                1L,
                345L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );

        SuspiciousCardTransferEntity actualCardEntity = mapper.toEntity(suspiciousCardTransferDto);
        SuspiciousCardTransferEntity expectedCardEntity = new SuspiciousCardTransferEntity(
                null,
                345L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );

        assertThat(actualCardEntity).isEqualTo(expectedCardEntity);
    }

    @Test
    @DisplayName("Маппинг в энтити, на вход подан null")
    void toEntityNullTest() {
        SuspiciousCardTransferEntity suspiciousCardTransferEntity = mapper.toEntity(null);
        assertNull(suspiciousCardTransferEntity);
    }

    @Test
    @DisplayName("Маппинг в дто")
    void toListDtoTest() {
        List<SuspiciousCardTransferEntity> entityList = new ArrayList<>();
        entityList.add(new SuspiciousCardTransferEntity(1L, 345L, false,
                false, "dont block", "dont suspicious"));
        entityList.add(new SuspiciousCardTransferEntity(2L, 231L, true,
                false, "dont block", "dont suspicious"));
        entityList.add(new SuspiciousCardTransferEntity(3L, 111L, false,
                true, "dont block", "dont suspicious"));

        List<SuspiciousCardTransferDto> actualListDto = mapper.toListDto(entityList);

        List<SuspiciousCardTransferDto> expectedDtoList = new ArrayList<>();
        expectedDtoList.add(new SuspiciousCardTransferDto(1L, 345L, false,
                false, "dont block", "dont suspicious"));
        expectedDtoList.add(new SuspiciousCardTransferDto(2L, 231L, true,
                false, "dont block", "dont suspicious"));
        expectedDtoList.add(new SuspiciousCardTransferDto(3L, 111L, false,
                true, "dont block", "dont suspicious"));

        assertThat(actualListDto).isEqualTo(expectedDtoList);
    }

    @Test
    @DisplayName("Маппинг в дто, на вход подан null")
    void toListDtoNullTest() {
        List<SuspiciousCardTransferDto> dtoList = mapper.toListDto(null);
        assertNull(dtoList);
    }

    @Test
    @DisplayName("Слияние в энтити")
    void mergeToEntityTest() {
        SuspiciousCardTransferDto suspiciousCardTransferDto = new SuspiciousCardTransferDto(
                1l,
                345L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );

        SuspiciousCardTransferEntity expectedEntity = new SuspiciousCardTransferEntity();
        expectedEntity.setCardTransferId(suspiciousCardTransferDto.getCardTransferId());
        expectedEntity.setIsBlocked(suspiciousCardTransferDto.getIsBlocked());
        expectedEntity.setIsSuspicious(suspiciousCardTransferDto.getIsSuspicious());
        expectedEntity.setBlockedReason(suspiciousCardTransferDto.getBlockedReason());
        expectedEntity.setSuspiciousReason(suspiciousCardTransferDto.getSuspiciousReason());

        SuspiciousCardTransferEntity actualEntity = new SuspiciousCardTransferEntity();
        actualEntity = mapper.mergeToEntity(suspiciousCardTransferDto, actualEntity);

        assertThat(actualEntity).isEqualTo(expectedEntity);
    }

    @Test
    @DisplayName("Слияние в энтити, на вход подан null")
    void mergeToEntityNullTest() {
        List<SuspiciousCardTransferDto> dtoList = mapper.toListDto(null);
        assertNull(dtoList);
    }
}