package com.example.server.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class NbpExchangeRateDTO {
  private String currency;
  private String code;
  private Double mid;
}
