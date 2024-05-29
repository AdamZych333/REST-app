package com.example.server.entity;


import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "request_logs")
public class RequestLog {
  @Id
  @GeneratedValue()
  private Long id;

  @Column(name = "currency", nullable = false)
  private String currency;
  
  @Column(name = "name", nullable = false)
  private String name;
  
  @Column(name = "date", nullable = false)
  private Timestamp date;
  
  @Column(name = "value1", nullable = false)
  private Double value;
}
