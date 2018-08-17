package io.kang.repository.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.kang.domain.redis.TodayRequest;

import java.io.IOException;
import java.util.List;

public interface TodayRequestRepository {
    public void create(TodayRequest todayRequest) throws JsonProcessingException;
    public TodayRequest findByLast() throws IOException;
    public List<TodayRequest> findAll();
    public void deleteByFirst();
    public long count();
}
