package com.example.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.naming.ServiceUnavailableException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.example.server.dto.NbpExchangeRateDTO;
import com.example.server.service.NbpExchangeratesService;

@SpringBootTest
public class NbpExchangeratesServiceTest {
  @Autowired
	private NbpExchangeratesService nbpExchangeratesService;

  @Test
	void whenValidCurrency_thenRateShouldBeFound() throws ServiceUnavailableException, NotFoundException{
    String currency = "USD";

    NbpExchangeRateDTO rate = this.nbpExchangeratesService.getExchangeRate(currency);

    assertNotNull(rate);
    assertEquals(currency, rate.getCode());
  }

  @Test
	void whenInvalidCurrency_thenExceptionShouldBeThrown() throws ServiceUnavailableException{
    String currency = "555";

    assertThrows(NotFoundException.class, () -> this.nbpExchangeratesService.getExchangeRate(currency));
  }
}
