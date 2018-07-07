package io.kang.unit_test.repository_unit;

import io.kang.domain.Age;
import io.kang.repository.AgeRepository;
import io.kang.test_config.JpaTestConfig;
import io.kang.unit_test.repository_unit.singleton_object.AgeCreateSingleton;
import io.kang.unit_test.repository_unit.singleton_object.AgeUpdateSingleton;
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
public class AgeRepositoryUnitTest {
    static final Logger logger = LoggerFactory.getLogger(AgeRepositoryUnitTest.class);

    @Autowired
    private AgeRepository ageRepository;

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
    public void age_find_all_test(){
        List<Age> ages = ageRepository.findAll();
        Assert.assertTrue(ages.size() != 0);
    }

    @Test
    public void age_get_one_success_test(){
        Age age = ageRepository.getOne(1L);
        Assert.assertTrue(age != null);
    }

    @Test(expected = LazyInitializationException.class)
    public void age_get_one_failure_test(){
        Age age = ageRepository.getOne(0L);
        age.toString();
    }

    @Test
    public void age_find_by_id_success_test(){
        Optional<Age> tmpAge = ageRepository.findById(1L);
        Assert.assertTrue(tmpAge.isPresent());
    }

    @Test
    public void age_find_by_id_failure_test(){
        Optional<Age> tmpAge = ageRepository.findById(0L);
        Assert.assertFalse(tmpAge.isPresent());
    }

    @Test
    @Transactional
    public void age_create_test(){
        Age age = AgeCreateSingleton.INSTANCE.getInstance();
        Age createAge = ageRepository.save(age);
        Assert.assertTrue(createAge.getId() != null);
    }

    @Test
    @Transactional
    public void age_update_test(){
        Age age = ageRepository.getOne(1L);
        Age updateAge = ageRepository.save(AgeUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(age, updateAge);
    }

    @Test
    @Transactional
    public void age_delete_test(){
        Age age = ageRepository.getOne(1L);
        ageRepository.delete(age);
        Optional<Age> afterAge = ageRepository.findById(1L);
        Assert.assertFalse(afterAge.isPresent());
    }

    @Test
    @Transactional
    public void age_delete_by_id_test(){
        ageRepository.deleteById(1L);
        Optional<Age> afterAge = ageRepository.findById(1L);
        Assert.assertFalse(afterAge.isPresent());
    }

    @Test
    public void age_exists_by_id_success_test(){
        Age age = ageRepository.getOne(1L);
        Assert.assertTrue(ageRepository.existsById(age.getId()));
    }

    @Test
    public void age_exists_by_id_failure_test(){
        Assert.assertFalse(ageRepository.existsById(0L));
    }

    @Test
    public void age_count_test(){
        long count = ageRepository.count();
        List<Age> ages = ageRepository.findAll();
        Assert.assertEquals(count, (long) ages.size());
    }
}
