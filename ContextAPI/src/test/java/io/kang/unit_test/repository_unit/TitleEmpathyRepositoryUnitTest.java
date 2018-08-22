package io.kang.unit_test.repository_unit;

import io.kang.domain.mysql.TitleEmpathy;
import io.kang.enumeration.Status;
import io.kang.repository.mysql.CategoryRepository;
import io.kang.repository.mysql.TitleEmpathyRepository;
import io.kang.repository.mysql.TitleRepository;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.TitleEmpathyCreateSingleton;
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
public class TitleEmpathyRepositoryUnitTest {
    static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @Autowired
    private TitleEmpathyRepository titleEmpathyRepository;

    @Autowired
    private TitleRepository titleRepository;

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
    public void title_empathy_find_all_test(){
        List<TitleEmpathy> titleEmpathys = titleEmpathyRepository.findAll();
        Assert.assertTrue(titleEmpathys.size() != 0);
    }

    @Test
    public void title_empathy_get_one_success_test(){
        Random random = new Random();
        List<TitleEmpathy> titleEmpathys = titleEmpathyRepository.findAll();
        TitleEmpathy titleEmpathy = titleEmpathyRepository.getOne(titleEmpathys.get(random.nextInt(titleEmpathys.size())).getId());
        Assert.assertTrue(titleEmpathy != null);
    }

    @Test(expected = LazyInitializationException.class)
    public void title_empathy_get_one_failure_test(){
        TitleEmpathy titleEmpathy = titleEmpathyRepository.getOne(0L);
        titleEmpathy.toString();
    }

    @Test
    public void title_empathy_find_by_id_success_test(){
        Random random = new Random();
        List<TitleEmpathy> titleEmpathys = titleEmpathyRepository.findAll();
        Optional<TitleEmpathy> tmpTitleEmpathy = titleEmpathyRepository.findById(titleEmpathys.get(random.nextInt(titleEmpathys.size())).getId());
        Assert.assertTrue(tmpTitleEmpathy.isPresent());
    }

    @Test
    public void title_empathy_find_by_id_failure_test(){
        Optional<TitleEmpathy> tmpTitleEmpathy = titleEmpathyRepository.findById(0L);
        Assert.assertFalse(tmpTitleEmpathy.isPresent());
    }

    @Test
    public void title_empathy_find_by_user_id_and_title_test(){
        Optional<TitleEmpathy> tmpTitleEmpathy = titleEmpathyRepository.findByUserIdAndTitle("kang123", titleRepository.getOne(1L));
        Assert.assertTrue(tmpTitleEmpathy.isPresent());
    }

    @Test
    @Transactional
    public void title_empathy_create_test(){
        TitleEmpathy titleEmpathy = TitleEmpathyCreateSingleton.INSTANCE.getInstance();
        titleEmpathy.setCheckedDate(LocalDateTime.now());
        TitleEmpathy createTitleEmpathy = titleEmpathyRepository.save(titleEmpathy);
        Assert.assertTrue(createTitleEmpathy != null);
    }

    @Test
    @Transactional
    public void title_empathy_update_test(){
        TitleEmpathy titleEmpathy = titleEmpathyRepository.getOne(8L);
        titleEmpathy.setCheckedDate(LocalDateTime.now());
        TitleEmpathy updateTitleEmpathy = titleEmpathyRepository.save(titleEmpathy);
        Assert.assertEquals(titleEmpathy, updateTitleEmpathy);
    }

    @Test
    @Transactional
    public void title_empathy_delete_test(){
        TitleEmpathy titleEmpathy = titleEmpathyRepository.getOne(8L);
        titleEmpathyRepository.delete(titleEmpathy);
        Optional<TitleEmpathy> afterTitleEmpathy = titleEmpathyRepository.findById(8L);
        Assert.assertFalse(afterTitleEmpathy.isPresent());
    }

    @Test
    @Transactional
    public void title_empathy_delete_by_id_test(){
        titleEmpathyRepository.deleteById(8L);
        Optional<TitleEmpathy> afterTitleEmpathy = titleEmpathyRepository.findById(8L);
        Assert.assertFalse(afterTitleEmpathy.isPresent());
    }

    @Test
    @Transactional
    public void delete_by_user_id_and_title_test(){
        titleEmpathyRepository.deleteByUserIdAndTitle("kang123", titleRepository.getOne(1L));
        Optional<TitleEmpathy> afterTitleEmpathy = titleEmpathyRepository.findByUserIdAndTitle("kang123", titleRepository.getOne(1L));
        Assert.assertFalse(afterTitleEmpathy.isPresent());
    }

    @Test
    public void title_empathy_exists_by_id_success_test(){
        TitleEmpathy titleEmpathy = titleEmpathyRepository.getOne(8L);
        Assert.assertTrue(titleEmpathyRepository.existsById(titleEmpathy.getId()));
    }

    @Test
    public void title_empathy_exists_by_id_failure_test(){
        Assert.assertFalse(titleEmpathyRepository.existsById(0L));
    }

    @Test
    public void title_empathy_exists_by_user_id_and_title_test(){
        Assert.assertTrue(titleEmpathyRepository.existsByUserIdAndTitle("kang123", titleRepository.getOne(1L)));
    }

    @Test
    public void title_empathy_exists_by_user_id_and_title_and_status_test(){
        Assert.assertTrue(titleEmpathyRepository.existsByUserIdAndTitleAndStatus("kang123", titleRepository.getOne(1L), Status.LIKE));
    }

    @Test
    public void title_empathy_count_test(){
        long count = titleEmpathyRepository.count();
        List<TitleEmpathy> titleEmpathys = titleEmpathyRepository.findAll();
        Assert.assertEquals(count, (long) titleEmpathys.size());
    }
}
