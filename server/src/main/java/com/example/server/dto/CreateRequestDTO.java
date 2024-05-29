package com.example.server.dto;


import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CreateRequestDTO {
  private String currency;
  
  private String name;
  
  private Timestamp date;
  
  private Double value;
}
