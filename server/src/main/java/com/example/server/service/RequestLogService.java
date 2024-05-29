package com.example.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.server.dto.CreateRequestDTO;
import com.example.server.dto.RequestLogDTO;
import com.example.server.entity.RequestLog;
import com.example.server.repository.RequestLogRepository;
import com.example.server.service.mapper.RequestLogMapper;

@Service
public class RequestLogService {
  private final RequestLogRepository requestLogRepository;
  private final RequestLogMapper requestLogMapper;

  @Autowired
  public RequestLogService(RequestLogRepository requestLogRepository, RequestLogMapper requestLogMapper){
    this.requestLogRepository = requestLogRepository;
    this.requestLogMapper = requestLogMapper;
  }

  public List<RequestLogDTO> getRequests(){
    List<RequestLog> requests = requestLogRepository.findAll();

    return this.requestLogMapper.toDto(requests);
  }

  public RequestLogDTO createRequest(CreateRequestDTO createRequestDTO){
    RequestLog requestLog = this.requestLogMapper.toEntityFromCreate(createRequestDTO);

    return this.requestLogMapper.toDto(this.requestLogRepository.save(requestLog));
  }
}
