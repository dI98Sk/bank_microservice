package com.bank.antifraud.mappers;

import com.bank.antifraud.dto.SuspiciousAccountTransferDto;
import com.bank.antifraud.entity.SuspiciousAccountTransferEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Тесты на маппер подозрительных переводов по номеру счета")
class SuspiciousAccountTransferMapperTest {

    private final SuspiciousAccountTransferMapper mapper = new SuspiciousAccountTransferMapperImpl();

    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest() {
        SuspiciousAccountTransferEntity suspiciousAccountTransferEntity = new SuspiciousAccountTransferEntity(
                1l,
                345L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );

        SuspiciousAccountTransferDto actualAccountDto = mapper.toDto(suspiciousAccountTransferEntity);
        SuspiciousAccountTransferDto expectedAccountDto = new SuspiciousAccountTransferDto(
                1L,
                345L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );

        assertThat(actualAccountDto).isEqualTo(expectedAccountDto);
    }

    @Test
    @DisplayName("Маппинг в дто, на вход подан null")
    void toDtoNullTest() {
        SuspiciousAccountTransferDto suspiciousAccountTransferDto = mapper.toDto(null);
        assertNull(suspiciousAccountTransferDto);
    }

    @Test
    @DisplayName("Маппинг в энтити")
    void toEntityTest() {
        SuspiciousAccountTransferDto suspiciousAccountTransferDto = new SuspiciousAccountTransferDto(
                1L,
                345L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );

        SuspiciousAccountTransferEntity actualAccountEntity = mapper.toEntity(suspiciousAccountTransferDto);
        SuspiciousAccountTransferEntity expectedAccountEntity = new SuspiciousAccountTransferEntity(
                null,
                345L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );

        assertThat(actualAccountEntity).isEqualTo(expectedAccountEntity);
    }

    @Test
    @DisplayName("Маппинг в энтити, на вход подан null")
    void toEntityNullTest() {
        SuspiciousAccountTransferEntity suspiciousAccountTransferEntity = mapper.toEntity(null);
        assertNull(suspiciousAccountTransferEntity);
    }

    @Test
    @DisplayName("Маппинг в дто")
    void toListDtoTest() {
        List<SuspiciousAccountTransferEntity> entityList = new ArrayList<>();
        entityList.add(new SuspiciousAccountTransferEntity(1L, 345L, false,
                false, "dont block", "dont suspicious"));
        entityList.add(new SuspiciousAccountTransferEntity(2L, 231L, true,
                false, "dont block", "dont suspicious"));
        entityList.add(new SuspiciousAccountTransferEntity(3L, 111L, false,
                true, "dont block", "dont suspicious"));

        List<SuspiciousAccountTransferDto> actualListDto = mapper.toListDto(entityList);

        List<SuspiciousAccountTransferDto> expectedDtoList = new ArrayList<>();
        expectedDtoList.add(new SuspiciousAccountTransferDto(1L, 345L, false,
                false, "dont block", "dont suspicious"));
        expectedDtoList.add(new SuspiciousAccountTransferDto(2L, 231L, true,
                false, "dont block", "dont suspicious"));
        expectedDtoList.add(new SuspiciousAccountTransferDto(3L, 111L, false,
                true, "dont block", "dont suspicious"));

        assertThat(actualListDto).isEqualTo(expectedDtoList);
    }

    @Test
    @DisplayName("Маппинг в дто, на вход подан null")
    void toListDtoNullTest() {
        List<SuspiciousAccountTransferDto> dtoList = mapper.toListDto(null);
        assertNull(dtoList);
    }

    @Test
    @DisplayName("Слияние в энтити")
    void mergeToEntityTest() {
        SuspiciousAccountTransferDto suspiciousAccountTransferDto = new SuspiciousAccountTransferDto(
                1L,
                345L,
                false,
                false,
                "dont block",
                "dont suspicious"
        );

        SuspiciousAccountTransferEntity expectedEntity = new SuspiciousAccountTransferEntity();
        expectedEntity.setAccountTransferId(suspiciousAccountTransferDto.getAccountTransferId());
        expectedEntity.setIsBlocked(suspiciousAccountTransferDto.getIsBlocked());
        expectedEntity.setIsSuspicious(suspiciousAccountTransferDto.getIsSuspicious());
        expectedEntity.setBlockedReason(suspiciousAccountTransferDto.getBlockedReason());
        expectedEntity.setSuspiciousReason(suspiciousAccountTransferDto.getSuspiciousReason());

        SuspiciousAccountTransferEntity actualEntity = new SuspiciousAccountTransferEntity();
        actualEntity = mapper.mergeToEntity(suspiciousAccountTransferDto, actualEntity);

        assertThat(actualEntity).isEqualTo(expectedEntity);
    }

    @Test
    @DisplayName("Слияние в энтити, на вход подан null")
    void mergeToEntityNullTest() {
        List<SuspiciousAccountTransferDto> dtoList = mapper.toListDto(null);
        assertNull(dtoList);
    }
}