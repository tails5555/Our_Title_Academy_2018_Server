package io.kang.service.domain_service.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.kang.domain.redis.TodayRequest;
import io.kang.dto.redis.TodayRequestDTO;

import java.io.IOException;
import java.util.List;

public interface TodayRequestService {
    public void create(final TodayRequestDTO todayRequestDTO) throws JsonProcessingException;
    public TodayRequestDTO findByLast() throws IOException;
    public List<TodayRequestDTO> findAll();
    public void deleteByFirst();
    public long count();
}