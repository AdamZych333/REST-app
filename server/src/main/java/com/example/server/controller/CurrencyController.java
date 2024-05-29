package com.example.server.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.naming.ServiceUnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.dto.CreateRequestDTO;
import com.example.server.dto.GetCurrencyRequestDTO;
import com.example.server.dto.GetCurrencyResponseDTO;
import com.example.server.dto.GetRequestsResponseDTO;
import com.example.server.dto.NbpExchangeRateDTO;
import com.example.server.dto.RequestLogDTO;
import com.example.server.service.NbpExchangeratesService;
import com.example.server.service.RequestLogService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path="/currencies")
public class CurrencyController {

  private final NbpExchangeratesService nbpExchangeratesService;
  private final RequestLogService requestLogService;

  @Autowired
  public CurrencyController(NbpExchangeratesService nbpExchangeratesService, RequestLogService requestLogService){
    this.nbpExchangeratesService = nbpExchangeratesService;
    this.requestLogService = requestLogService;
  }
  
  @PostMapping("/get-current-currency-value-command")
  public ResponseEntity<GetCurrencyResponseDTO> getCurrentCurrencyValueCommand(@Valid @RequestBody GetCurrencyRequestDTO getCurrencyRequestDTO) {
    try{
      NbpExchangeRateDTO rate = nbpExchangeratesService.getExchangeRate(getCurrencyRequestDTO.getCurrency());

      CreateRequestDTO createRequestDTO = new CreateRequestDTO(
        getCurrencyRequestDTO.getCurrency(),
        getCurrencyRequestDTO.getName(),
        new Timestamp(System.currentTimeMillis()),
        rate.getMid()
      );
      this.requestLogService.createRequest(createRequestDTO);

      return new ResponseEntity<>(new GetCurrencyResponseDTO(rate.getMid()), HttpStatus.OK);
    }catch(ServiceUnavailableException ex){
      return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    } catch (NotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/requests")
  public ResponseEntity<GetRequestsResponseDTO> getRequests(){
    List<RequestLogDTO> requests = this.requestLogService.getRequests();

    GetRequestsResponseDTO response = new GetRequestsResponseDTO(requests);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
