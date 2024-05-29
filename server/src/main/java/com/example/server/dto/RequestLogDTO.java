package com.example.server.dto;


import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class RequestLogDTO {
  private Long id;

  private String currency;
  
  private String name;
  
  private Timestamp date;
  
  private Double value;
}
