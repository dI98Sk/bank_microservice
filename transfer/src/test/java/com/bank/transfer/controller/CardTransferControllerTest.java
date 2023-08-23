package com.bank.transfer.controller;

import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.service.CardTransferService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CardTransferControllerTest {
    @InjectMocks
    CardTransferController controller;
    @Mock
    CardTransferService service;

    private final CardTransferDto expectedCardTransferDto = getCardTransferDto();


    @Test
    @DisplayName("Чтение по id, позитивный сценарий")
    void readByIdPositiveTest(){
        Mockito.when(service.findById(ArgumentMatchers.anyLong())).thenReturn(expectedCardTransferDto);
        CardTransferDto cardTransferDtoActual = controller.read(ArgumentMatchers.anyLong());
        Assertions.assertEquals(expectedCardTransferDto, cardTransferDtoActual);
    }

    @Test
    @DisplayName("Чтение по несуществующему id, негативный сценарий")
    void readByNonExistIdNegativeTest(){
        Mockito.when(service.findById(ArgumentMatchers.anyLong())).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> controller.read(ArgumentMatchers.anyLong())
        );
    }

    @Test
    @DisplayName("Создание, позитивный сценарий")
    void createPositiveTest(){
        Mockito.when(service.save(ArgumentMatchers.any(CardTransferDto.class))).thenReturn(expectedCardTransferDto);
        ResponseEntity<CardTransferDto> response = controller.create(expectedCardTransferDto);
        Assertions.assertEquals(expectedCardTransferDto, response.getBody());
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest(){
        Mockito.when(service.update(ArgumentMatchers.anyLong(),ArgumentMatchers.any(CardTransferDto.class)))
                .thenReturn(expectedCardTransferDto);
        ResponseEntity<CardTransferDto> response = controller.update(1L, expectedCardTransferDto);
        Assertions.assertEquals(expectedCardTransferDto, response.getBody());
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest(){
        Mockito.when(service.update(ArgumentMatchers.anyLong(), ArgumentMatchers.any(CardTransferDto.class)))
                .thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> controller.update(1L, expectedCardTransferDto));
    }


    @Test
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void readAllByIdPositiveTest(){
        Mockito.when(service.findAllById(ArgumentMatchers.anyList()))
                .thenReturn(List.of(expectedCardTransferDto));
        ResponseEntity<List<CardTransferDto>> response = controller.readAll(ArgumentMatchers.anyList());
        Assertions.assertEquals(List.of(expectedCardTransferDto), response.getBody());
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
    }



    private CardTransferDto getCardTransferDto() {
        return new CardTransferDto(
                1L,
                2L,
                BigDecimal.TEN,
                "purpose",
                3L
        );
    }

}
