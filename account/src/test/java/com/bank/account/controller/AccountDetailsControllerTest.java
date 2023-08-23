package com.bank.account.controller;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.service.AccountDetailsService;
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
class AccountDetailsControllerTest {
    @InjectMocks
    private AccountDetailsController controller;
    @Mock
    private AccountDetailsService service;
    private final AccountDetailsDto expectedAccountDetailsDto = getAccountDetailsDto();

    @Test
    @DisplayName("Чтение по id, позитивный сценарий")
    void readByIdPositiveTest() {
        Mockito.when(service.findById(ArgumentMatchers.anyLong())).thenReturn(expectedAccountDetailsDto);
        AccountDetailsDto actualAccountDetailsDto = controller.read(ArgumentMatchers.anyLong());
        Assertions.assertEquals(expectedAccountDetailsDto, actualAccountDetailsDto);
    }

    @Test
    @DisplayName("Чтение по несуществующему id, негативный сценарий")
    void readByNonExistIdNegativeTest() {
        Mockito.when(service.findById(ArgumentMatchers.anyLong())).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(EntityNotFoundException.class, () -> controller.read(ArgumentMatchers.anyLong()));
    }

    @Test
    @DisplayName("Создание, позитивный сценарий")
    void createPositiveTest() {
        Mockito.when(service.save(ArgumentMatchers.any(AccountDetailsDto.class)))
                .thenReturn(expectedAccountDetailsDto);
        ResponseEntity<AccountDetailsDto> response = controller.create(expectedAccountDetailsDto);
        Assertions.assertEquals(expectedAccountDetailsDto, response.getBody());
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        Mockito.when(service.update(ArgumentMatchers.anyLong(), ArgumentMatchers.any(AccountDetailsDto.class)))
                .thenReturn(expectedAccountDetailsDto);
        ResponseEntity<AccountDetailsDto> response = controller.update(1L, expectedAccountDetailsDto);
        Assertions.assertEquals(expectedAccountDetailsDto, response.getBody());
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        Mockito.when(service.update(ArgumentMatchers.anyLong(), ArgumentMatchers.any(AccountDetailsDto.class)))
                .thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> controller.update(1L, expectedAccountDetailsDto));

    }

    @Test
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void readAllByIdPositiveTest() {
        Mockito.when(service.findAllById(ArgumentMatchers.anyList())).thenReturn(List.of(expectedAccountDetailsDto));
        ResponseEntity<List<AccountDetailsDto>> response = controller.readAll(ArgumentMatchers.anyList());
        Assertions.assertEquals(List.of(expectedAccountDetailsDto), response.getBody());
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
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
}