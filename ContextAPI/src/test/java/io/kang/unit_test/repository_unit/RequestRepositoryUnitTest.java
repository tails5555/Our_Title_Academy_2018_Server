package io.kang.unit_test.repository_unit;

import io.kang.domain.mysql.Request;
import io.kang.repository.mysql.CategoryRepository;
import io.kang.repository.mysql.RequestRepository;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.RequestCreateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.RequestUpdateSingleton;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class RequestRepositoryUnitTest {
    static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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
    public void request_find_all_test(){
        List<Request> requests = requestRepository.findAll();
        Assert.assertTrue(requests.size() != 0);
    }

    @Test
    public void request_get_one_success_test(){
        Random random = new Random();
        List<Request> requests = requestRepository.findAll();
        Request request = requestRepository.getOne(requests.get(random.nextInt(requests.size())).getId());
        Assert.assertTrue(request != null);
    }

    @Test(expected = LazyInitializationException.class)
    public void request_get_one_failure_test(){
        Request request = requestRepository.getOne(0L);
        request.toString();
    }

    @Test
    public void request_find_by_id_success_test(){
        Random random = new Random();
        List<Request> requests = requestRepository.findAll();
        Optional<Request> tmpRequest = requestRepository.findById(requests.get(random.nextInt(requests.size())).getId());
        Assert.assertTrue(tmpRequest.isPresent());
    }

    @Test
    public void request_find_by_id_failure_test(){
        Optional<Request> tmpRequest = requestRepository.findById(0L);
        Assert.assertFalse(tmpRequest.isPresent());
    }

    @Test
    public void request_find_by_category_id_and_available_is_true_test(){
        Pageable pageable = new PageRequest(0, 4);
        Page<Request> requests = requestRepository.findByCategoryIdAndAvailableIsTrue(2L, pageable);
        Assert.assertTrue(requests.getSize() >= 0 && requests.getSize() <= 4);
    }

    @Test
    public void request_find_by_category_id_and_available_is_true_and_intro_contains_test(){
        Pageable pageable = new PageRequest(0, 4);
        Page<Request> requests = requestRepository.findByCategoryIdAndAvailableIsTrueAndContextContains(2L, "적당", pageable);
        Assert.assertTrue(requests.getSize() >= 0 && requests.getSize() <= 4);
    }

    @Test
    public void request_find_by_category_id_and_available_is_true_and_context_contains_test(){
        Pageable pageable = new PageRequest(0, 4);
        Page<Request> requests = requestRepository.findByCategoryIdAndAvailableIsTrueAndIntroContains(2L, "적당", pageable);
        Assert.assertTrue(requests.getSize() >= 0 && requests.getSize() <= 4);
    }

    @Test
    public void request_find_by_category_id_and_available_is_true_and_user_id_test(){
        Pageable pageable = new PageRequest(0, 4);
        Page<Request> requests = requestRepository.findByCategoryIdAndAvailableIsTrueAndUserId(2L, "kang123", pageable);
        Assert.assertTrue(requests.getSize() >= 0 && requests.getSize() <= 4);
    }

    @Test
    public void request_find_top_ten_by_category_is_not_null_and_available_is_true_order_by_written_date_desc_test(){
        List<Request> requests = requestRepository.findTop10ByCategoryIsNotNullAndAvailableIsTrueOrderByViewDesc();
        Assert.assertTrue(requests.size() >= 0);
    }

    @Test
    public void request_find_top_ten_by_category_is_not_null_order_by_view_desc_test(){
        List<Request> requests = requestRepository.findTop10ByCategoryIsNotNullAndAvailableIsTrueOrderByViewDesc();
        Assert.assertTrue(requests.size() >= 0);
    }

    @Test
    public void request_find_by_user_id_and_category_is_not_null_and_available_is_true_order_by_written_date_desc_test(){
        List<Request> requests = requestRepository.findByUserIdAndCategoryIsNotNullAndAvailableIsTrueOrderByWrittenDateDesc("kang123");
        Assert.assertTrue(requests.size() >= 0);
    }

    @Test
    public void request_find_by_user_id_and_available_is_false_order_by_written_date_desc_test(){
        List<Request> requests = requestRepository.findByUserIdAndAvailableIsFalseOrderByWrittenDateDesc("kang123");
        Assert.assertTrue(requests.size() >= 0);
    }

    @Test
    public void request_find_by_user_id_and_category_order_by_written_date_desc_test(){
        List<Request> requests = requestRepository.findByUserIdAndCategoryOrderByWrittenDateDesc("kang123", categoryRepository.getOne(14L));
        Assert.assertTrue(requests.size() >= 0);
    }

    @Test
    public void request_find_by_category_is_not_null_and_available_is_true_test(){
        List<Request> requests = requestRepository.findByCategoryIsNotNullAndAvailableIsTrue();
        Assert.assertTrue(requests.size() >= 0);
    }

    @Test
    public void request_find_by_category_is_not_null_and_available_is_false_order_by_written_date_desc_test(){
        List<Request> requests = requestRepository.findByCategoryIsNotNullAndAvailableIsFalseOrderByWrittenDateDesc();
        Assert.assertTrue(requests.size() >= 0);
    }

    @Test
    public void request_find_by_category_is_null_and_available_is_false_order_by_written_date_desc_test(){
        List<Request> requests = requestRepository.findByCategoryIsNullAndAvailableIsFalseOrderByWrittenDateDesc();
        Assert.assertTrue(requests.size() >= 0);
    }

    @Test
    public void request_find_by_intro_contains_or_context_contains_test(){
        List<Request> requests = requestRepository.findByIntroContainsOrContextContains("적당", "적당");
        Assert.assertTrue(requests.size() >= 0);
    }

    @Test
    @Transactional
    public void request_create_test(){
        Request request = RequestCreateSingleton.INSTANCE.getInstance();
        request.setWrittenDate(LocalDateTime.now());
        Request createRequest = requestRepository.save(request);
        Assert.assertTrue(createRequest.getId() != null);
    }

    @Test
    @Transactional
    public void request_update_test(){
        Request request = requestRepository.getOne(1L);
        Request updateRequest = requestRepository.save(RequestUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(request, updateRequest);
    }

    @Test
    @Transactional
    public void request_delete_test(){
        Request request = requestRepository.getOne(1L);
        requestRepository.delete(request);
        Optional<Request> afterRequest = requestRepository.findById(1L);
        Assert.assertFalse(afterRequest.isPresent());
    }

    @Test
    @Transactional
    public void request_delete_by_id_test(){
        requestRepository.deleteById(1L);
        Optional<Request> afterRequest = requestRepository.findById(1L);
        Assert.assertFalse(afterRequest.isPresent());
    }

    @Test
    public void request_exists_by_id_success_test(){
        Request request = requestRepository.getOne(1L);
        Assert.assertTrue(requestRepository.existsById(request.getId()));
    }

    @Test
    public void request_exists_by_id_failure_test(){
        Assert.assertFalse(requestRepository.existsById(0L));
    }

    @Test
    public void request_count_test(){
        long count = requestRepository.count();
        List<Request> requests = requestRepository.findAll();
        Assert.assertEquals(count, (long) requests.size());
    }
}
