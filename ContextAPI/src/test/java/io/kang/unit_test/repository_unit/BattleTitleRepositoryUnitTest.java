package io.kang.unit_test.repository_unit;

import io.kang.repository.redis.BattleTitleRepository;
import io.kang.repository.redis.BattleTitleRepositoryImpl;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, RedisTestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BattleTitleRepositoryUnitTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private BattleTitleRepository battleTitleRepository;

    @Before
    public void set_up(){
        battleTitleRepository = new BattleTitleRepositoryImpl(this.redisTemplate);
    }

    @Test
    @Transactional
    public void battle_title_create_test() throws IOException {
        List<Long> battleTitles = battleTitleRepository.findAll();
        battleTitleRepository.create(1L);
        List<Long> afterBattleTitles = battleTitleRepository.findAll();
        Assert.assertEquals(battleTitles.size() + 1, afterBattleTitles.size());
    }

    @Test
    @Transactional
    public void battle_title_find_all_test() throws IOException {
        List<Long> battleTitles = battleTitleRepository.findAll();
        Assert.assertTrue(battleTitles.size() >= 0);
    }

    @Test
    @Transactional
    public void battle_title_delete_all_test() throws IOException {
        battleTitleRepository.deleteAll();
        Assert.assertTrue(battleTitleRepository.findAll().size() == 0);
    }

    @Test
    @Transactional
    public void battle_title_delete_by_title_id_test() throws IOException {
        Random random = new Random();
        List<Long> battleTitles = battleTitleRepository.findAll();
        battleTitleRepository.deleteByTitleId(battleTitles.get(random.nextInt(battleTitles.size())));
        List<Long> afterBattleTitles = battleTitleRepository.findAll();
        Assert.assertEquals(afterBattleTitles.size(), battleTitles.size() - 1);
    }

    @Test
    public void battle_title_count_test() throws IOException {
        List<Long> battleTitles = battleTitleRepository.findAll();
        Assert.assertEquals(battleTitles.size(), battleTitleRepository.count());
    }
}
