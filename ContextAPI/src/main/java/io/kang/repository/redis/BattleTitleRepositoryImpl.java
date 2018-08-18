package io.kang.repository.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BattleTitleRepositoryImpl implements BattleTitleRepository{
    private RedisTemplate<String, String> redisTemplate;
    private static final String BATTLE_TITLE_KEY = "BattleTitleCache";

    @Autowired
    public BattleTitleRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void create(final Long titleId) {
        this.redisTemplate.opsForList().leftPush(BATTLE_TITLE_KEY, Long.toString(titleId));
    }

    @Override
    public List<Long> findAll() {
        return this.redisTemplate.opsForList().range(BATTLE_TITLE_KEY, 0, -1).stream()
                .map(str -> Long.parseLong(str))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        this.redisTemplate.opsForList().getOperations().delete(BATTLE_TITLE_KEY);
    }

    @Override
    public void deleteByTitleId(final Long titleId){
        this.redisTemplate.opsForList().remove(BATTLE_TITLE_KEY, 1, String.valueOf(titleId));
    }

    @Override
    public long count() {
        return this.redisTemplate.opsForList().size(BATTLE_TITLE_KEY);
    }
}
