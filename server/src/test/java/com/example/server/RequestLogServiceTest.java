package com.example.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.server.dto.CreateRequestDTO;
import com.example.server.dto.RequestLogDTO;
import com.example.server.service.RequestLogService;

@SpringBootTest
public class RequestLogServiceTest {
  @Autowired
	private RequestLogService requestLogService;

  @Test
  void createRequest_shouldReturnCreatedRequest(){
    CreateRequestDTO createRequestDTO = new CreateRequestDTO(
        "Test",
        "Test test",
        new Timestamp(System.currentTimeMillis()),
        1.5
      );

    RequestLogDTO requestLogDTO = requestLogService.createRequest(createRequestDTO);

    assertEquals(createRequestDTO.getCurrency(), requestLogDTO.getCurrency());
    assertEquals(createRequestDTO.getDate(), requestLogDTO.getDate());
    assertEquals(createRequestDTO.getName(), requestLogDTO.getName());
    assertEquals(createRequestDTO.getValue(), requestLogDTO.getValue());
  }

  @Test
  void afterCreateRequest_getRequestsShouldReturnCreatedRequest(){
    CreateRequestDTO createRequestDTO = new CreateRequestDTO(
        "Test",
        "Test test",
        new Timestamp(System.currentTimeMillis()),
        1.5
      );

    RequestLogDTO requestLogDTO = requestLogService.createRequest(createRequestDTO);

    List<RequestLogDTO> requestLogDTOs = this.requestLogService.getRequests();

    RequestLogDTO request = requestLogDTOs.stream().filter((r) -> r.getId() == requestLogDTO.getId()).findFirst().orElseThrow();
    assertEquals(createRequestDTO.getCurrency(), request.getCurrency());
    assertEquals(createRequestDTO.getDate(), request.getDate());
    assertEquals(createRequestDTO.getName(), request.getName());
    assertEquals(createRequestDTO.getValue(), request.getValue());
  }
}
