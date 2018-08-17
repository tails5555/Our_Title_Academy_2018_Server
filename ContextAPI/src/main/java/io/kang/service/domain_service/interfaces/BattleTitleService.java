package io.kang.service.domain_service.interfaces;

import java.util.List;

public interface BattleTitleService {
    public void create(final Long titleId);
    public List<Long> findAll();
    public void deleteAll();
    public void deleteByTitleId(final Long titleId);
    public long count();
}
