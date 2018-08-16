package io.kang.repository.redis;

import io.kang.domain.redis.TodayRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodayRequestRepository extends CrudRepository<TodayRequest, Long> {
    public boolean existsByRequestId(Long requestId);
}
