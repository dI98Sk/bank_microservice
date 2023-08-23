package com.bank.transfer.controller;

import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.service.AccountTransferService;
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
public class AccountTransferControllerTest {

    @InjectMocks
    private AccountTransferController controller;
    @Mock
    private AccountTransferService service;
    private final AccountTransferDto expectedAccountTransferDto = getAccountTransferDto();

    @Test
    @DisplayName("Чтение по id, позитивный сценарий")
    void readByIdPositiveTest(){
        Mockito.when(service.findById(ArgumentMatchers.anyLong())).thenReturn(expectedAccountTransferDto);
        AccountTransferDto accountTransferDtoActual = controller.read(ArgumentMatchers.anyLong());
        Assertions.assertEquals(expectedAccountTransferDto, accountTransferDtoActual);
    }

    @Test
    @DisplayName("Чтение по несуществующему id, негативный сценарий")
    void readByNonExistIdNegativeTest(){
        Mockito.when(service.findById(ArgumentMatchers.anyLong())).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> controller.read(ArgumentMatchers.anyLong()));
    }


    @Test
    @DisplayName("Создание, позитивный сценарий")
    void createPositiveTest(){
        Mockito.when(service.save(ArgumentMatchers.any(AccountTransferDto.class)))
                .thenReturn(expectedAccountTransferDto);
        ResponseEntity<AccountTransferDto> response = controller.create(expectedAccountTransferDto);
        Assertions.assertEquals(expectedAccountTransferDto, response.getBody());
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
    }


    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest(){
        Mockito.when(service.update(ArgumentMatchers.anyLong(), ArgumentMatchers.any(AccountTransferDto.class)))
                .thenReturn(expectedAccountTransferDto);
        ResponseEntity<AccountTransferDto> response = controller.update(1L, expectedAccountTransferDto);
        Assertions.assertEquals(expectedAccountTransferDto, response.getBody());
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest(){
        Mockito.when(service.update(ArgumentMatchers.anyLong(), ArgumentMatchers.any(AccountTransferDto.class)))
                .thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> controller.update(1L, expectedAccountTransferDto));
    }


    @Test
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void readAllByIdPositiveTest(){
        Mockito.when(service.findAllById(ArgumentMatchers.anyList()))
                .thenReturn(List.of(expectedAccountTransferDto));
        ResponseEntity<List<AccountTransferDto>> response = controller.readAll(ArgumentMatchers.anyList());
        Assertions.assertEquals(List.of(expectedAccountTransferDto), response.getBody());
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
    }




    private AccountTransferDto getAccountTransferDto() {
        return new AccountTransferDto(
                1L,
                2L,
                BigDecimal.TEN,
                "purpose",
                3L
        );
    }
}
