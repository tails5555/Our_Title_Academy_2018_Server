package io.kang.unit_test.repository_unit;

import io.kang.domain.mysql.Category;
import io.kang.domain.mysql.Request;
import io.kang.domain.mysql.Title;
import io.kang.repository.mysql.CategoryRepository;
import io.kang.repository.mysql.RequestRepository;
import io.kang.repository.mysql.TitleRepository;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.TitleCreateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.TitleUpdateSingleton;
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
public class TitleRepositoryUnitTest {
    static final Logger logger = LoggerFactory.getLogger(TitleRepository.class);

    private static Random random = new Random();

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RequestRepository requestRepository;

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
    public void title_find_all_test(){
        List<Title> titles = titleRepository.findAll();
        Assert.assertTrue(titles.size() != 0);
    }

    @Test
    public void title_get_one_success_test(){
        Random random = new Random();
        List<Title> titles = titleRepository.findAll();
        Title title = titleRepository.getOne(titles.get(random.nextInt(titles.size())).getId());
        Assert.assertTrue(title != null);
    }

    @Test(expected = LazyInitializationException.class)
    public void title_get_one_failure_test(){
        Title title = titleRepository.getOne(0L);
        title.toString();
    }

    @Test
    public void title_find_by_id_success_test(){
        Random random = new Random();
        List<Title> titles = titleRepository.findAll();
        Optional<Title> tmpTitle = titleRepository.findById(titles.get(random.nextInt(titles.size())).getId());
        Assert.assertTrue(tmpTitle.isPresent());
    }

    @Test
    public void title_find_by_id_failure_test(){
        Optional<Title> tmpTitle = titleRepository.findById(0L);
        Assert.assertFalse(tmpTitle.isPresent());
    }

    @Test
    public void title_find_top_by_request_id_order_by_like_count_desc_test(){
        Optional<Title> tmpTitle = titleRepository.findTopByRequestIdOrderByLikeCountDesc(1L);
        Assert.assertTrue(tmpTitle.isPresent());
    }

    @Test
    public void title_find_top_five_by_request_id_order_by_like_count_desc_test(){
        List<Title> titles = titleRepository.findTop5ByRequestIdOrderByLikeCountDesc(1L);
        Assert.assertTrue(titles.size() > 0);
    }

    @Test
    public void title_find_all_by_order_by_written_date_desc_test(){
        List<Title> titles = titleRepository.findAllByOrderByWrittenDateDesc();
        Assert.assertTrue(titles.size() > 0);
    }

    @Test
    public void title_find_by_user_id_order_by_written_date_desc_test(){
        List<Title> titles = titleRepository.findByUserIdOrderByWrittenDateDesc("kang123");
        Assert.assertTrue(titles.size() > 0);
    }

    @Test
    public void title_find_by_user_id_and_request_test(){
        Request request = requestRepository.getOne(1L);
        Optional<Title> tmpTitle = titleRepository.findByUserIdAndRequest("kang123", request);
        Assert.assertFalse(tmpTitle.isPresent());
    }

    @Test
    public void title_find_by_user_id_and_request_category_test(){
        Category category = categoryRepository.getOne(14L);
        List<Title> titles = titleRepository.findByUserIdAndRequestCategory("kang123", category);
        Assert.assertTrue(titles.size() > 0);
    }

    @Test
    public void title_find_by_context_contains_test(){
        List<Title> titles = titleRepository.findByContextContains("아");
        Assert.assertTrue(titles.size() > 0);
    }

    @Test
    @Transactional
    public void title_create_test(){
        Title title = TitleCreateSingleton.INSTANCE.getInstance();
        title.setWrittenDate(LocalDateTime.now());
        Title createTitle = titleRepository.save(title);
        Assert.assertTrue(createTitle.getId() != null);
    }

    @Test
    @Transactional
    public void title_update_test(){
        Title title = titleRepository.getOne(1L);
        Title updateTitle = titleRepository.save(TitleUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(title, updateTitle);
    }

    @Test
    @Transactional
    public void title_delete_test(){
        Title title = titleRepository.getOne(1L);
        titleRepository.delete(title);
        Optional<Title> afterTitle = titleRepository.findById(1L);
        Assert.assertFalse(afterTitle.isPresent());
    }

    @Test
    @Transactional
    public void title_delete_by_id_test(){
        titleRepository.deleteById(1L);
        Optional<Title> afterTitle = titleRepository.findById(1L);
        Assert.assertFalse(afterTitle.isPresent());
    }

    @Test
    public void title_exists_by_id_success_test(){
        Title title = titleRepository.getOne(1L);
        Assert.assertTrue(titleRepository.existsById(title.getId()));
    }

    @Test
    public void title_exists_by_id_failure_test(){
        Assert.assertFalse(titleRepository.existsById(0L));
    }

    @Test
    public void title_count_test(){
        long count = titleRepository.count();
        List<Title> titles = titleRepository.findAll();
        Assert.assertEquals(count, (long) titles.size());
    }

    @Test
    public void title_count_by_request_test(){
        List<Title> titles = titleRepository.findByRequestOrderByWrittenDateDesc(requestRepository.getOne(1L));
        long count = titleRepository.countByRequest(requestRepository.getOne(1L));
        Assert.assertEquals(count, (long) titles.size());
    }
}
