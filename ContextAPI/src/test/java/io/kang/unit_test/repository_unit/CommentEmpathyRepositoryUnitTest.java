package io.kang.unit_test.repository_unit;

import io.kang.domain.mysql.CommentEmpathy;
import io.kang.enumeration.Status;
import io.kang.repository.mysql.CategoryRepository;
import io.kang.repository.mysql.CommentEmpathyRepository;
import io.kang.repository.mysql.CommentRepository;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.CommentEmpathyCreateSingleton;
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
public class CommentEmpathyRepositoryUnitTest {
    static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);
    
    @Autowired
    private CommentEmpathyRepository commentEmpathyRepository;
    
    @Autowired
    private CommentRepository commentRepository;

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
    public void comment_empathy_find_all_test(){
        List<CommentEmpathy> commentEmpathys = commentEmpathyRepository.findAll();
        Assert.assertTrue(commentEmpathys.size() != 0);
    }

    @Test
    public void comment_empathy_get_one_success_test(){
        Random random = new Random();
        List<CommentEmpathy> commentEmpathys = commentEmpathyRepository.findAll();
        CommentEmpathy commentEmpathy = commentEmpathyRepository.getOne(commentEmpathys.get(random.nextInt(commentEmpathys.size())).getId());
        Assert.assertTrue(commentEmpathy != null);
    }

    @Test(expected = LazyInitializationException.class)
    public void comment_empathy_get_one_failure_test(){
        CommentEmpathy commentEmpathy = commentEmpathyRepository.getOne(0L);
        commentEmpathy.toString();
    }

    @Test
    public void comment_empathy_find_by_id_success_test(){
        Random random = new Random();
        List<CommentEmpathy> commentEmpathys = commentEmpathyRepository.findAll();
        Optional<CommentEmpathy> tmpCommentEmpathy = commentEmpathyRepository.findById(commentEmpathys.get(random.nextInt(commentEmpathys.size())).getId());
        Assert.assertTrue(tmpCommentEmpathy.isPresent());
    }

    @Test
    public void comment_empathy_find_by_id_failure_test(){
        Optional<CommentEmpathy> tmpCommentEmpathy = commentEmpathyRepository.findById(0L);
        Assert.assertFalse(tmpCommentEmpathy.isPresent());
    }

    @Test
    public void comment_empathy_find_by_user_id_and_comment_test(){
        Optional<CommentEmpathy> tmpCommentEmpathy = commentEmpathyRepository.findByUserIdAndComment("kang123", commentRepository.getOne(5L));
        Assert.assertTrue(tmpCommentEmpathy.isPresent());
    }

    @Test
    @Transactional
    public void comment_empathy_create_test(){
        CommentEmpathy commentEmpathy = CommentEmpathyCreateSingleton.INSTANCE.getInstance();
        commentEmpathy.setCheckedDate(LocalDateTime.now());
        CommentEmpathy createCommentEmpathy = commentEmpathyRepository.save(commentEmpathy);
        Assert.assertTrue(createCommentEmpathy != null);
    }

    @Test
    @Transactional
    public void comment_empathy_update_test(){
        CommentEmpathy commentEmpathy = commentEmpathyRepository.getOne(79L);
        commentEmpathy.setCheckedDate(LocalDateTime.now());
        CommentEmpathy updateCommentEmpathy = commentEmpathyRepository.save(commentEmpathy);
        Assert.assertEquals(commentEmpathy, updateCommentEmpathy);
    }

    @Test
    @Transactional
    public void comment_empathy_delete_test(){
        CommentEmpathy commentEmpathy = commentEmpathyRepository.getOne(79L);
        commentEmpathyRepository.delete(commentEmpathy);
        Optional<CommentEmpathy> afterCommentEmpathy = commentEmpathyRepository.findById(79L);
        Assert.assertFalse(afterCommentEmpathy.isPresent());
    }

    @Test
    @Transactional
    public void comment_empathy_delete_by_id_test(){
        commentEmpathyRepository.deleteById(79L);
        Optional<CommentEmpathy> afterCommentEmpathy = commentEmpathyRepository.findById(79L);
        Assert.assertFalse(afterCommentEmpathy.isPresent());
    }

    @Test
    @Transactional
    public void delete_by_user_id_and_comment_test(){
        commentEmpathyRepository.deleteByUserIdAndComment("kang123", commentRepository.getOne(5L));
        Optional<CommentEmpathy> afterCommentEmpathy = commentEmpathyRepository.findByUserIdAndComment("kang123", commentRepository.getOne(5L));
        Assert.assertFalse(afterCommentEmpathy.isPresent());
    }

    @Test
    public void comment_empathy_exists_by_id_success_test(){
        CommentEmpathy commentEmpathy = commentEmpathyRepository.getOne(79L);
        Assert.assertTrue(commentEmpathyRepository.existsById(commentEmpathy.getId()));
    }

    @Test
    public void comment_empathy_exists_by_id_failure_test(){
        Assert.assertFalse(commentEmpathyRepository.existsById(0L));
    }

    @Test
    public void comment_empathy_exists_by_user_id_and_comment_test(){
        Assert.assertTrue(commentEmpathyRepository.existsByUserIdAndComment("kang123", commentRepository.getOne(5L)));
    }

    @Test
    public void comment_empathy_exists_by_user_id_and_comment_and_status_test(){
        Assert.assertTrue(commentEmpathyRepository.existsByUserIdAndCommentAndStatus("kang123", commentRepository.getOne(5L), Status.LIKE));
    }

    @Test
    public void comment_empathy_count_test(){
        long count = commentEmpathyRepository.count();
        List<CommentEmpathy> commentEmpathys = commentEmpathyRepository.findAll();
        Assert.assertEquals(count, (long) commentEmpathys.size());
    }
}
