package io.kang.unit_test.repository_unit;

import io.kang.domain.City;
import io.kang.repository.AgeRepository;
import io.kang.repository.CityRepository;
import io.kang.test_config.JpaTestConfig;
import io.kang.unit_test.repository_unit.singleton_object.CityCreateSingleton;
import io.kang.unit_test.repository_unit.singleton_object.CityUpdateSingleton;
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
public class CityRepositoryUnitTest {
    static final Logger logger = LoggerFactory.getLogger(AgeRepositoryUnitTest.class);

    @Autowired
    private CityRepository cityRepository;

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
    public void city_find_all_test(){
        List<City> cities = cityRepository.findAll();
        Assert.assertTrue(cities.size() != 0);
    }

    @Test
    public void city_get_one_success_test(){
        City city = cityRepository.getOne(1L);
        Assert.assertTrue(city != null);
    }

    @Test(expected = LazyInitializationException.class)
    public void city_get_one_failure_test(){
        City city = cityRepository.getOne(0L);
        city.toString();
    }

    @Test
    public void city_find_by_id_success_test(){
        Optional<City> tmpCity = cityRepository.findById(1L);
        Assert.assertTrue(tmpCity.isPresent());
    }

    @Test
    public void city_find_by_id_failure_test(){
        Optional<City> tmpCity = cityRepository.findById(0L);
        Assert.assertFalse(tmpCity.isPresent());
    }

    @Test
    @Transactional
    public void city_create_test(){
        City city = CityCreateSingleton.INSTANCE.getInstance();
        City createCity = cityRepository.save(city);
        Assert.assertTrue(createCity.getId() != 0L);
    }

    @Test
    @Transactional
    public void city_update_test(){
        City city = cityRepository.getOne(1L);
        City updateCity = cityRepository.save(CityUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(city, updateCity);
    }

    @Test
    @Transactional
    public void city_delete_test(){
        City city = cityRepository.getOne(1L);
        cityRepository.delete(city);
        Optional<City> tmpCity = cityRepository.findById(1L);
        Assert.assertFalse(tmpCity.isPresent());
    }

    @Test
    @Transactional
    public void city_delete_by_id_test(){
        cityRepository.deleteById(1L);
        Optional<City> tmpCity = cityRepository.findById(1L);
        Assert.assertFalse(tmpCity.isPresent());
    }

    @Test
    public void city_exists_by_id_success_test(){
        City city = cityRepository.getOne(1L);
        Assert.assertTrue(cityRepository.existsById(city.getId()));
    }

    @Test
    public void city_exists_by_id_failure_test(){
        Assert.assertFalse(cityRepository.existsById(0L));
    }

    @Test
    public void city_count_test(){
        long count = cityRepository.count();
        List<City> cities = cityRepository.findAll();
        Assert.assertEquals(count, (long) cities.size());
    }
}
