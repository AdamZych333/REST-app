package com.example.server.service;

import java.util.Optional;

import javax.naming.ServiceUnavailableException;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.server.dto.NbpExchangeRateDTO;
import com.example.server.dto.NbpExchangeRatesTableDTO;

@Service
public class NbpExchangeratesService {
  public NbpExchangeRateDTO getExchangeRate(String currency) throws ServiceUnavailableException, NotFoundException{
    NbpExchangeRatesTableDTO[] response = this.getRatesTable();

    if(response == null || response[0] == null || response[0].getRates() == null){
      throw new NotFoundException();
    }
      
    Optional<NbpExchangeRateDTO> rate = response[0].getRates()
      .stream()
      .filter(r -> r.getCode().equalsIgnoreCase(currency))
      .findFirst();

    return rate.orElseThrow(() -> new NotFoundException());
  }

  private NbpExchangeRatesTableDTO[] getRatesTable() throws ServiceUnavailableException{
    RestTemplate restTemplate = new RestTemplate();
    try{
      NbpExchangeRatesTableDTO[] response = restTemplate.getForObject("https://api.nbp.pl/api/exchangerates/tables/A?format=json", NbpExchangeRatesTableDTO[].class);

      return response;
    }catch(Exception ex){
      throw new ServiceUnavailableException();
    }
  }
}
