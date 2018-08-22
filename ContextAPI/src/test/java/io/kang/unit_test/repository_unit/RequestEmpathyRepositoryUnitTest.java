package io.kang.unit_test.repository_unit;

import io.kang.domain.mysql.RequestEmpathy;
import io.kang.enumeration.Status;
import io.kang.repository.mysql.CategoryRepository;
import io.kang.repository.mysql.RequestEmpathyRepository;
import io.kang.repository.mysql.RequestRepository;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.RequestEmpathyCreateSingleton;
import org.hibernate.LazyInitializationException;
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

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, RedisTestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RequestEmpathyRepositoryUnitTest {
    static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @Autowired
    private RequestEmpathyRepository requestEmpathyRepository;

    @Autowired
    private RequestRepository requestRepository;

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
    public void request_empathy_find_all_test(){
        List<RequestEmpathy> requestEmpathys = requestEmpathyRepository.findAll();
        Assert.assertTrue(requestEmpathys.size() != 0);
    }

    @Test
    public void request_empathy_get_one_success_test(){
        Random random = new Random();
        List<RequestEmpathy> requestEmpathys = requestEmpathyRepository.findAll();
        RequestEmpathy requestEmpathy = requestEmpathyRepository.getOne(requestEmpathys.get(random.nextInt(requestEmpathys.size())).getId());
        Assert.assertTrue(requestEmpathy != null);
    }

    @Test(expected = LazyInitializationException.class)
    public void request_empathy_get_one_failure_test(){
        RequestEmpathy requestEmpathy = requestEmpathyRepository.getOne(0L);
        requestEmpathy.toString();
    }

    @Test
    public void request_empathy_find_by_id_success_test(){
        Random random = new Random();
        List<RequestEmpathy> requestEmpathys = requestEmpathyRepository.findAll();
        Optional<RequestEmpathy> tmpRequestEmpathy = requestEmpathyRepository.findById(requestEmpathys.get(random.nextInt(requestEmpathys.size())).getId());
        Assert.assertTrue(tmpRequestEmpathy.isPresent());
    }

    @Test
    public void request_empathy_find_by_id_failure_test(){
        Optional<RequestEmpathy> tmpRequestEmpathy = requestEmpathyRepository.findById(0L);
        Assert.assertFalse(tmpRequestEmpathy.isPresent());
    }

    @Test
    public void request_empathy_find_by_user_id_and_request_test(){
        Optional<RequestEmpathy> tmpRequestEmpathy = requestEmpathyRepository.findByUserIdAndRequest("kang123", requestRepository.getOne(8L));
        Assert.assertTrue(tmpRequestEmpathy.isPresent());
    }

    @Test
    @Transactional
    public void request_empathy_create_test(){
        RequestEmpathy requestEmpathy = RequestEmpathyCreateSingleton.INSTANCE.getInstance();
        requestEmpathy.setCheckedDate(LocalDateTime.now());
        RequestEmpathy createRequestEmpathy = requestEmpathyRepository.save(requestEmpathy);
        Assert.assertTrue(createRequestEmpathy != null);
    }

    @Test
    @Transactional
    public void request_empathy_update_test(){
        RequestEmpathy requestEmpathy = requestEmpathyRepository.getOne(30L);
        requestEmpathy.setCheckedDate(LocalDateTime.now());
        RequestEmpathy updateRequestEmpathy = requestEmpathyRepository.save(requestEmpathy);
        Assert.assertEquals(requestEmpathy, updateRequestEmpathy);
    }

    @Test
    @Transactional
    public void request_empathy_delete_test(){
        RequestEmpathy requestEmpathy = requestEmpathyRepository.getOne(30L);
        requestEmpathyRepository.delete(requestEmpathy);
        Optional<RequestEmpathy> afterRequestEmpathy = requestEmpathyRepository.findById(30L);
        Assert.assertFalse(afterRequestEmpathy.isPresent());
    }

    @Test
    @Transactional
    public void request_empathy_delete_by_id_test(){
        requestEmpathyRepository.deleteById(30L);
        Optional<RequestEmpathy> afterRequestEmpathy = requestEmpathyRepository.findById(30L);
        Assert.assertFalse(afterRequestEmpathy.isPresent());
    }

    @Test
    @Transactional
    public void delete_by_user_id_and_request_test(){
        requestEmpathyRepository.deleteByUserIdAndRequest("kang123", requestRepository.getOne(8L));
        Optional<RequestEmpathy> afterRequestEmpathy = requestEmpathyRepository.findByUserIdAndRequest("kang123", requestRepository.getOne(8L));
        Assert.assertFalse(afterRequestEmpathy.isPresent());
    }

    @Test
    public void request_empathy_exists_by_id_success_test(){
        RequestEmpathy requestEmpathy = requestEmpathyRepository.getOne(30L);
        Assert.assertTrue(requestEmpathyRepository.existsById(requestEmpathy.getId()));
    }

    @Test
    public void request_empathy_exists_by_id_failure_test(){
        Assert.assertFalse(requestEmpathyRepository.existsById(0L));
    }

    @Test
    public void request_empathy_exists_by_user_id_and_request_test(){
        Assert.assertTrue(requestEmpathyRepository.existsByUserIdAndRequest("kang123", requestRepository.getOne(8L)));
    }

    @Test
    public void request_empathy_exists_by_user_id_and_request_and_status_test(){
        Assert.assertTrue(requestEmpathyRepository.existsByUserIdAndRequestAndStatus("kang123", requestRepository.getOne(8L), Status.LIKE));
    }

    @Test
    public void request_empathy_count_test(){
        long count = requestEmpathyRepository.count();
        List<RequestEmpathy> requestEmpathys = requestEmpathyRepository.findAll();
        Assert.assertEquals(count, (long) requestEmpathys.size());
    }
}
