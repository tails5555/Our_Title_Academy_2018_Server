package io.kang.service.domain_service.interfaces;

import io.kang.dto.redis.TodayRankDTO;

import java.util.List;

public interface TodayRankService {
    public List<TodayRankDTO> findAll();
    public TodayRankDTO findById(final Long id);
    public TodayRankDTO findByRequestId(final Long requestId);
    public TodayRankDTO create(final TodayRankDTO todayRankDTO);
    public TodayRankDTO update(final TodayRankDTO todayRankDTO);
    public void deleteById(final Long id);
    public void deleteAll();
    public boolean existsById(final Long id);
    public long count();
}
