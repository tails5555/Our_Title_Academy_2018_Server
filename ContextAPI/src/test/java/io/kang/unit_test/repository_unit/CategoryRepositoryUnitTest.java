package io.kang.unit_test.repository_unit;

import io.kang.domain.mysql.Category;
import io.kang.repository.mysql.CategoryRepository;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.CategoryCreateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.CategoryUpdateSingleton;
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
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, RedisTestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryUnitTest {
    static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    private static Random random = new Random();

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
    public void category_find_all_test(){
        List<Category> categories = categoryRepository.findAll();
        Assert.assertTrue(categories.size() != 0);
    }

    @Test
    public void category_get_one_success_test(){
        Random random = new Random();
        List<Category> categories = categoryRepository.findAll();
        Category category = categoryRepository.getOne(categories.get(random.nextInt(categories.size())).getId());
        Assert.assertTrue(category != null);
    }

    @Test(expected = LazyInitializationException.class)
    public void category_get_one_failure_test(){
        Category category = categoryRepository.getOne(0L);
        category.toString();
    }

    @Test
    public void category_find_by_id_success_test(){
        Random random = new Random();
        List<Category> categories = categoryRepository.findAll();
        Optional<Category> tmpCategory = categoryRepository.findById(categories.get(random.nextInt(categories.size())).getId());
        Assert.assertTrue(tmpCategory.isPresent());
    }

    @Test
    public void category_find_by_id_failure_test(){
        Optional<Category> tmpCategory = categoryRepository.findById(0L);
        Assert.assertFalse(tmpCategory.isPresent());
    }

    @Test
    @Transactional
    public void category_create_test(){
        Category category = CategoryCreateSingleton.INSTANCE.getInstance();
        Category createCategory = categoryRepository.save(category);
        Assert.assertTrue(createCategory.getId() != null);
    }

    @Test
    @Transactional
    public void category_update_test(){
        Category category = categoryRepository.getOne(1L);
        Category updateCategory = categoryRepository.save(CategoryUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(category, updateCategory);
    }

    @Test
    @Transactional
    public void category_delete_test(){
        Category category = categoryRepository.getOne(1L);
        categoryRepository.delete(category);
        Optional<Category> afterCategory = categoryRepository.findById(1L);
        Assert.assertFalse(afterCategory.isPresent());
    }

    @Test
    @Transactional
    public void category_delete_by_id_test(){
        categoryRepository.deleteById(1L);
        Optional<Category> afterCategory = categoryRepository.findById(1L);
        Assert.assertFalse(afterCategory.isPresent());
    }

    @Test
    public void category_exists_by_id_success_test(){
        Category category = categoryRepository.getOne(1L);
        Assert.assertTrue(categoryRepository.existsById(category.getId()));
    }

    @Test
    public void category_exists_by_id_failure_test(){
        Assert.assertFalse(categoryRepository.existsById(0L));
    }

    @Test
    public void category_count_test(){
        long count = categoryRepository.count();
        List<Category> categories = categoryRepository.findAll();
        Assert.assertEquals(count, (long) categories.size());
    }
}
