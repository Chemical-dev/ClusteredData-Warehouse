package com.clustereddatawarehouse;

import com.clustereddatawarehouse.controller.DealsController;
import com.clustereddatawarehouse.dto.request.AddDealDto;
import com.clustereddatawarehouse.dto.response.ErrorResponse;
import com.clustereddatawarehouse.repository.DealsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClusteredDataWarehouseApplicationTests {

    @Autowired
    private DealsController dealService;

    @MockBean
    private DealsRepository dealRepository;


    @Test
    public void whenValidDeal_thenDealShouldBeAdded() {
        AddDealDto dto = new AddDealDto();
        dto.setDealUniqueId("12345");
        dto.setFromCurrencyIsoCode("USD");
        dto.setToCurrencyIsoCode("EUR");
        dto.setDealTimestamp(LocalDateTime.now());
        dto.setDealAmount(new BigDecimal("1000"));

        when(dealRepository.existsByDealUniqueId(dto.getDealUniqueId())).thenReturn(false);

        ResponseEntity<?> response = dealService.createDeal(dto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testAddInvalidDeal() {
        AddDealDto invalidDto = new AddDealDto();
        invalidDto.setDealUniqueId("657734793843");
        invalidDto.setFromCurrencyIsoCode("null");
        invalidDto.setToCurrencyIsoCode("EUR");
        invalidDto.setDealTimestamp(LocalDateTime.now());
        invalidDto.setDealAmount(new BigDecimal("1000.00"));

        ResponseEntity<?> response = dealService.createDeal(invalidDto);
        System.out.println("response: " + response);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("FromCurrency and ToCurrency should be 3 letters length.", ((ErrorResponse) response.getBody()).getMessage());
    }

    @Test
    public void testAddDealWithInvalidCurrencyCode() {
        AddDealDto invalidCurrencyDto = new AddDealDto();
        invalidCurrencyDto.setDealUniqueId("D3");
        invalidCurrencyDto.setFromCurrencyIsoCode("US");
        invalidCurrencyDto.setToCurrencyIsoCode("EUR");
        invalidCurrencyDto.setDealTimestamp(LocalDateTime.now());
        invalidCurrencyDto.setDealAmount(new BigDecimal("1000.00"));

        ResponseEntity<?> response = dealService.createDeal(invalidCurrencyDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("FromCurrency and ToCurrency should be 3 letters length.", ((ErrorResponse) response.getBody()).getMessage());
    }

    @Test
    public void testAddDuplicateDeal() {
        AddDealDto duplicateDto = new AddDealDto();
        duplicateDto.setDealUniqueId("D2");
        duplicateDto.setFromCurrencyIsoCode("USD");
        duplicateDto.setToCurrencyIsoCode("EUR");
        duplicateDto.setDealTimestamp(LocalDateTime.now());
        duplicateDto.setDealAmount(new BigDecimal("1000.00"));

        when(dealRepository.existsByDealUniqueIdOrDealAmountOrFromCurrencyIsoCodeOrToCurrencyIsoCode(
                duplicateDto.getDealUniqueId(), duplicateDto.getDealAmount(),
                duplicateDto.getFromCurrencyIsoCode(), duplicateDto.getToCurrencyIsoCode())).thenReturn(true);

        ResponseEntity<?> response = dealService.createDeal(duplicateDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Deal already exists",
                ((ErrorResponse) response.getBody()).getMessage());
    }
}
