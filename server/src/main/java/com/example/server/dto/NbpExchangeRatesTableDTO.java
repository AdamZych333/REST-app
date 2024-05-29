package com.example.server.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class NbpExchangeRatesTableDTO {
  private String table;
  private String no;
  private String effectiveDate;
  private List<NbpExchangeRateDTO> rates;


}
