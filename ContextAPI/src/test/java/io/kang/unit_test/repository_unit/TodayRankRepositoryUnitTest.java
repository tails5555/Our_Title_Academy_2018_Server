package io.kang.unit_test.repository_unit;

import io.kang.domain.mysql.Request;
import io.kang.domain.redis.TodayRank;
import io.kang.repository.mysql.RequestRepository;
import io.kang.repository.redis.TodayRankRepository;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.TodayRankCreateSingleton;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runner.RunWith;
import org.junit.runners.model.FrameworkMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, RedisTestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodayRankRepositoryUnitTest {
    @Autowired
    private RequestRepository requestRepository;
    
    @Autowired
    private TodayRankRepository todayRankRepository;

    static final Logger logger = LoggerFactory.getLogger(TodayRankRepository.class);

    private static Random random = new Random();
    
    @Rule
    public MethodRule watchman = new TestWatchman() {
        public void starting(FrameworkMethod method) {
            logger.info("[Log] - Run Test {}...", method.getName());
        }
        public void succeeded(FrameworkMethod method) {
            logger.info("[Log] - Test {} succeeded.", method.getName());
        }
        public void failed(Throwable e, FrameworkMethod method) {
            logger.error("[Log] - Test {} failed with {}.", method.getName(), e);
        }
    };

    @Test
    public void today_rank_find_all_test(){
        List<TodayRank> todayRanks = StreamSupport.stream(todayRankRepository.findAll().spliterator(), false)
                                        .collect(Collectors.toList());
        Assert.assertTrue(todayRanks.size() != 0);
    }

    @Test
    public void today_rank_find_by_id_success_test(){
        Random random = new Random();
        List<TodayRank> todayRanks = StreamSupport.stream(todayRankRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        Optional<TodayRank> tmpTodayRank = todayRankRepository.findById(todayRanks.get(random.nextInt(todayRanks.size())).getId());
        Assert.assertTrue(tmpTodayRank.isPresent());
    }

    @Test
    public void today_rank_find_by_id_failure_test(){
        Optional<TodayRank> tmpTodayRank = todayRankRepository.findById(0L);
        Assert.assertFalse(tmpTodayRank.isPresent());
    }

    @Test
    public void today_rank_find_by_request_success_test() {
        List<TodayRank> todayRanks = StreamSupport.stream(todayRankRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        Optional<TodayRank> tmpTodayRank = todayRankRepository.findById(todayRanks.get(random.nextInt(todayRanks.size())).getId());
        Request request = requestRepository.getOne(tmpTodayRank.get().getRequestId());
        Optional<TodayRank> tmpTodayRankWithRequest = todayRankRepository.findByRequestId(request.getId());
        Assert.assertTrue(tmpTodayRankWithRequest.isPresent());
    }

    @Test
    @Transactional
    public void today_rank_create_test(){
        TodayRank todayRank = TodayRankCreateSingleton.INSTANCE.getInstance();
        TodayRank createTodayRank = todayRankRepository.save(todayRank);
        Assert.assertTrue(createTodayRank.getId() != null);
    }

    @Test
    @Transactional
    public void today_rank_update_test(){
        Random random = new Random();
        List<TodayRank> todayRanks = StreamSupport.stream(todayRankRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        Optional<TodayRank> tmpTodayRank = todayRankRepository.findById(todayRanks.get(random.nextInt(todayRanks.size())).getId());
        TodayRank updateTodayRank = todayRankRepository.save(tmpTodayRank.get());
        Assert.assertEquals(tmpTodayRank.get(), updateTodayRank);
    }

    @Test
    @Transactional
    public void today_rank_delete_test(){
        List<TodayRank> todayRanks = StreamSupport.stream(todayRankRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        Optional<TodayRank> tmpTodayRank = todayRankRepository.findById(todayRanks.get(random.nextInt(todayRanks.size())).getId());
        todayRankRepository.delete(tmpTodayRank.get());
        Optional<TodayRank> afterTodayRank = todayRankRepository.findById(tmpTodayRank.get().getId());
        Assert.assertFalse(afterTodayRank.isPresent());
    }

    @Test
    @Transactional
    public void today_rank_delete_by_id_test(){
        todayRankRepository.deleteById(1L);
        Optional<TodayRank> afterTodayRank = todayRankRepository.findById(1L);
        Assert.assertFalse(afterTodayRank.isPresent());
    }

    @Test
    public void today_rank_exists_by_id_success_test(){
        List<TodayRank> todayRanks = StreamSupport.stream(todayRankRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        Optional<TodayRank> tmpTodayRank = todayRankRepository.findById(todayRanks.get(random.nextInt(todayRanks.size())).getId());
        Assert.assertTrue(todayRankRepository.existsById(tmpTodayRank.get().getId()));
    }

    @Test
    public void today_rank_exists_by_id_failure_test(){
        Assert.assertFalse(todayRankRepository.existsById(0L));
    }

    @Test
    public void today_rank_count_test(){
        long count = todayRankRepository.count();
        List<TodayRank> todayRanks = StreamSupport.stream(todayRankRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        Assert.assertEquals(count, (long) todayRanks.size());
    }
}
