package io.kang.unit_test.repository_unit;

import io.kang.domain.redis.TodayRequest;
import io.kang.repository.redis.TodayRequestRepository;
import io.kang.repository.redis.TodayRequestRepositoryImpl;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.TodayRequestTempSingleton;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, RedisTestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodayRequestRepositoryUnitTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private TodayRequestRepository todayRequestRepository;

    @Before
    public void set_up(){
        todayRequestRepository = new TodayRequestRepositoryImpl(this.redisTemplate);
    }

    @Test
    @Transactional
    public void today_request_create_test() throws IOException {
        TodayRequest todayRequest = TodayRequestTempSingleton.INSTANCE.getInstance();
        todayRequestRepository.create(TodayRequestTempSingleton.INSTANCE.getInstance());
        Assert.assertEquals(todayRequest, todayRequestRepository.findByLast());
    }

    @Test
    public void today_request_find_by_last_test() throws IOException {
        Assert.assertTrue(todayRequestRepository.findByLast() != null);
    }

    @Test
    public void today_request_find_all_test() throws IOException {
        List<TodayRequest> todayRequests = todayRequestRepository.findAll();
        Assert.assertTrue(todayRequests.size() > 0);
    }

    @Test
    @Transactional
    public void today_request_delete_by_first_test() throws IOException {
        List<TodayRequest> todayRequests = todayRequestRepository.findAll();
        todayRequestRepository.deleteByFirst();
        List<TodayRequest> afterTodayRequests = todayRequestRepository.findAll();
        Assert.assertEquals(afterTodayRequests.size(), todayRequests.size() - 1);
    }

    @Test
    public void today_request_count_test() throws IOException {
        List<TodayRequest> todayRequests = todayRequestRepository.findAll();
        Assert.assertEquals(todayRequests.size(), todayRequestRepository.count());
    }
}
