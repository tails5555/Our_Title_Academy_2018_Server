package io.kang.repository.redis;

import java.util.List;

public interface BattleTitleRepository {
    public void create(final Long titleId);
    public List<Long> findAll();
    public void deleteAll();
    public void deleteByTitleId(final Long titleId);
    public long count();
}
