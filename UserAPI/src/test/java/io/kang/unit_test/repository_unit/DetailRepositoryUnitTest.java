package io.kang.unit_test.repository_unit;

import io.kang.domain.Detail;
import io.kang.repository.DetailRepository;
import io.kang.test_config.JpaTestConfig;
import io.kang.unit_test.singleton_object.repository_testing.DetailCreateSingleton;
import io.kang.unit_test.singleton_object.repository_testing.DetailUpdateSingleton;
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
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaTestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DetailRepositoryUnitTest {
    static final Logger logger = LoggerFactory.getLogger(AgeRepositoryUnitTest.class);

    @Autowired
    private DetailRepository detailRepository;

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
    public void detail_find_all_test(){
        List<Detail> detail = detailRepository.findAll();
        Assert.assertTrue(detail.size() != 0);
    }

    @Test
    public void detail_get_one_success_test(){
        Detail detail = detailRepository.getOne(1L);
        Assert.assertTrue(detail != null);
    }

    @Test(expected = LazyInitializationException.class)
    public void detail_get_one_failure_test(){
        Detail detail = detailRepository.getOne(0L);
        detail.toString();
    }

    @Test
    public void detail_find_by_id_success_test(){
        Optional<Detail> tmpDetail = detailRepository.findById(1L);
        Assert.assertTrue(tmpDetail.isPresent());
    }

    @Test
    public void detail_find_by_id_failure_test(){
        Optional<Detail> tmpDetail = detailRepository.findById(0L);
        Assert.assertFalse(tmpDetail.isPresent());
    }

    @Test
    public void detail_find_by_user_login_id_success_test(){
        Optional<Detail> tmpDetail = detailRepository.findByUserLoginId("kang123");
        Assert.assertTrue(tmpDetail.isPresent());
    }

    @Test
    public void detail_find_by_user_login_id_failure_test(){
        Optional<Detail> tmpDetail = detailRepository.findByUserLoginId("");
        Assert.assertFalse(tmpDetail.isPresent());
    }

    @Test
    @Transactional
    public void detail_create_test(){
        Detail detail = DetailCreateSingleton.INSTANCE.getInstance();
        Detail createDetail = detailRepository.save(detail);
        Assert.assertTrue(createDetail.getId() != null);
    }

    @Test
    @Transactional
    public void detail_update_test(){
        Detail detail = detailRepository.getOne(1L);
        Detail updateDetail = detailRepository.save(DetailUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(detail, updateDetail);
    }

    @Test
    @Transactional
    public void detail_delete_test(){
        Detail detail = detailRepository.getOne(1L);
        detailRepository.delete(detail);
        Optional<Detail> tmpDetail = detailRepository.findById(1L);
        Assert.assertFalse(tmpDetail.isPresent());
    }

    @Test
    @Transactional
    public void detail_delete_by_id_test(){
        detailRepository.deleteById(1L);
        Optional<Detail> tmpDetail = detailRepository.findById(1L);
        Assert.assertFalse(tmpDetail.isPresent());
    }

    @Test
    public void detail_exists_by_id_success_test(){
        Detail detail = detailRepository.getOne(1L);
        Assert.assertTrue(detailRepository.existsById(detail.getId()));
    }

    @Test
    public void detail_exists_by_id_failure_test(){
        Assert.assertFalse(detailRepository.existsById(0L));
    }

    @Test
    public void detail_count_test(){
        long count = detailRepository.count();
        List<Detail> details = detailRepository.findAll();
        Assert.assertEquals(count, (long) details.size());
    }
}
