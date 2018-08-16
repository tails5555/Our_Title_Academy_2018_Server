package io.kang.service.domain_service.interfaces;

import io.kang.dto.redis.TodayRequestDTO;

import java.util.List;

public interface TodayRequestService {
    public List<TodayRequestDTO> findAll();
    public TodayRequestDTO findById(final Long id);
    public TodayRequestDTO create(final TodayRequestDTO todayRequestDTO);
    public TodayRequestDTO update(final TodayRequestDTO todayRequestDTO);
    public void deleteById(final Long id);
    public void deleteAll();
    public boolean existsById(final Long id);
    public boolean existsByRequestId(final Long requestId);
    public long count();
}
