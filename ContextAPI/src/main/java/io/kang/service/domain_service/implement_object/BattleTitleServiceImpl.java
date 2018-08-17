package io.kang.service.domain_service.implement_object;

import io.kang.repository.redis.BattleTitleRepository;
import io.kang.service.domain_service.interfaces.BattleTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BattleTitleServiceImpl implements BattleTitleService {
    @Autowired
    private BattleTitleRepository battleTitleRepository;

    @Override
    public void create(final Long titleId) {
        battleTitleRepository.create(titleId);
    }

    @Override
    public List<Long> findAll() {
        return battleTitleRepository.findAll();
    }

    @Override
    public void deleteAll() {
        battleTitleRepository.deleteAll();
    }

    @Override
    public void deleteByTitleId(final Long titleId) {
        battleTitleRepository.deleteByTitleId(titleId);
    }

    @Override
    public long count() {
        return battleTitleRepository.count();
    }
}
