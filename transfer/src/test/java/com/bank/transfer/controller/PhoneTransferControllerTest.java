package com.bank.transfer.controller;

import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.service.PhoneTransferService;
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
public class PhoneTransferControllerTest {
    @InjectMocks
    private PhoneTransferController controller;
    @Mock
    private PhoneTransferService service;

    private final PhoneTransferDto expectedPhoneTransferDto = getPhoneTransferDto();


    @Test
    @DisplayName("Чтение по id, позитивный сценарий")
    void readByIdPositiveTest(){
        Mockito.when(service.findById(ArgumentMatchers.anyLong())).thenReturn(expectedPhoneTransferDto);
        PhoneTransferDto phoneTransferDtoActual = controller.read(ArgumentMatchers.anyLong());
        Assertions.assertEquals(expectedPhoneTransferDto, phoneTransferDtoActual);
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
        Mockito.when(service.save(ArgumentMatchers.any(PhoneTransferDto.class))).thenReturn(expectedPhoneTransferDto);
        ResponseEntity<PhoneTransferDto> response = controller.create(expectedPhoneTransferDto);
        Assertions.assertEquals(expectedPhoneTransferDto, response.getBody());
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest(){
        Mockito.when(service.update(ArgumentMatchers.anyLong(), ArgumentMatchers.any(PhoneTransferDto.class)))
                .thenReturn(expectedPhoneTransferDto);
        ResponseEntity<PhoneTransferDto> response = controller.update(1L, expectedPhoneTransferDto);
        Assertions.assertEquals(expectedPhoneTransferDto, response.getBody());
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest(){
        Mockito.when(service.update(ArgumentMatchers.anyLong(),ArgumentMatchers.any(PhoneTransferDto.class)))
                .thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> controller.update(1L, expectedPhoneTransferDto));
    }

    @Test
    @DisplayName("Чтение по нескольким id, позитивный сценарий")
    void readAllByIdPositiveTest(){
        Mockito.when(service.findAllById(ArgumentMatchers.anyList())).thenReturn(List.of(expectedPhoneTransferDto));
        ResponseEntity<List<PhoneTransferDto>> response = controller.readAll(ArgumentMatchers.anyList());
        Assertions.assertEquals(List.of(expectedPhoneTransferDto), response.getBody());
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
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


}
