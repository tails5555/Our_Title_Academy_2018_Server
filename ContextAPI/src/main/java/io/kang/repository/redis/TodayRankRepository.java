package io.kang.repository.redis;

import io.kang.domain.redis.TodayRank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodayRankRepository extends CrudRepository<TodayRank, Long>{
    public Optional<TodayRank> findByRequestId(Long requestId);
}
