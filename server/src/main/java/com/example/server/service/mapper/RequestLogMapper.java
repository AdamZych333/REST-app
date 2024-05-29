package com.example.server.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.server.dto.CreateRequestDTO;
import com.example.server.dto.RequestLogDTO;
import com.example.server.entity.RequestLog;

@Service
public class RequestLogMapper implements EntityMapper<RequestLog, RequestLogDTO> {

  @Override
  public RequestLog toEntity(RequestLogDTO dto) {
    RequestLog request = new RequestLog();
    request.setCurrency(dto.getCurrency());
    request.setDate(dto.getDate());
    request.setId(dto.getId());
    request.setName(dto.getName());
    request.setValue(dto.getValue());
    
    return request;
  }

  @Override
  public RequestLogDTO toDto(RequestLog entity) {
    RequestLogDTO requestDTO = new RequestLogDTO();
    requestDTO.setCurrency(entity.getCurrency());
    requestDTO.setDate(entity.getDate());
    requestDTO.setId(entity.getId());
    requestDTO.setName(entity.getName());
    requestDTO.setValue(entity.getValue());
    
    return requestDTO;
  }

  @Override
  public List<RequestLog> toEntity(List<RequestLogDTO> dtoList) {
    return dtoList.stream().map(dto -> this.toEntity(dto)).collect(Collectors.toList());
  }

  @Override
  public List<RequestLogDTO> toDto(List<RequestLog> entityList) {
    return entityList.stream().map(dto -> this.toDto(dto)).collect(Collectors.toList());
  }

  public RequestLog toEntityFromCreate(CreateRequestDTO createRequestDTO){
    RequestLog request = new RequestLog();
    request.setCurrency(createRequestDTO.getCurrency());
    request.setDate(createRequestDTO.getDate());
    request.setName(createRequestDTO.getName());
    request.setValue(createRequestDTO.getValue());
    
    return request;
  }
}
