package com.example.server.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class GetCurrencyRequestDTO {
  @NotEmpty(message = "The currency is required.") private String currency;
  @NotEmpty(message = "The name is required.") private String name;
}
