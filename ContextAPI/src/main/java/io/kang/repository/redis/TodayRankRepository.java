package io.kang.repository.redis;

import io.kang.domain.redis.TodayRank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodayRankRepository extends CrudRepository<TodayRank, Long> {
}
