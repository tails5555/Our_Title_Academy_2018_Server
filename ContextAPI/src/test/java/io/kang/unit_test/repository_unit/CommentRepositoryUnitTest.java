package io.kang.unit_test.repository_unit;

import io.kang.domain.mysql.Comment;
import io.kang.repository.mysql.CategoryRepository;
import io.kang.repository.mysql.CommentRepository;
import io.kang.repository.mysql.RequestRepository;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.CommentCreateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.CommentUpdateSingleton;
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
public class CommentRepositoryUnitTest {
    static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    private static Random random = new Random();

    @Autowired
    private CommentRepository commentRepository;

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
    public void comment_find_all_test(){
        List<Comment> comments = commentRepository.findAll();
        Assert.assertTrue(comments.size() != 0);
    }

    @Test
    public void comment_get_one_success_test(){
        Random random = new Random();
        List<Comment> comments = commentRepository.findAll();
        Comment comment = commentRepository.getOne(comments.get(random.nextInt(comments.size())).getId());
        Assert.assertTrue(comment != null);
    }

    @Test(expected = LazyInitializationException.class)
    public void comment_get_one_failure_test(){
        Comment comment = commentRepository.getOne(0L);
        comment.toString();
    }

    @Test
    public void comment_find_by_id_success_test(){
        Random random = new Random();
        List<Comment> comments = commentRepository.findAll();
        Optional<Comment> tmpComment = commentRepository.findById(comments.get(random.nextInt(comments.size())).getId());
        Assert.assertTrue(tmpComment.isPresent());
    }

    @Test
    public void comment_find_by_id_failure_test(){
        Optional<Comment> tmpComment = commentRepository.findById(0L);
        Assert.assertFalse(tmpComment.isPresent());
    }

    @Test
    public void comment_find_by_user_id_order_by_written_date_desc_test(){
        List<Comment> comments = commentRepository.findByUserIdOrderByWrittenDateDesc("kang123");
        Assert.assertTrue(comments.size() != 0);
    }

    @Test
    public void find_by_request_order_by_written_date_desc_test(){
        List<Comment> comments = commentRepository.findByRequestOrderByWrittenDateDesc(requestRepository.getOne(1L));
        Assert.assertTrue(comments.size() != 0);
    }

    @Test
    @Transactional
    public void comment_create_test(){
        Comment comment = CommentCreateSingleton.INSTANCE.getInstance();
        comment.setWrittenDate(LocalDateTime.now());
        Comment createComment = commentRepository.save(comment);
        Assert.assertTrue(createComment.getId() != null);
    }

    @Test
    @Transactional
    public void comment_update_test(){
        Comment comment = commentRepository.getOne(1L);
        Comment updateComment = commentRepository.save(CommentUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(comment, updateComment);
    }

    @Test
    @Transactional
    public void comment_delete_test(){
        Comment comment = commentRepository.getOne(1L);
        commentRepository.delete(comment);
        Optional<Comment> afterComment = commentRepository.findById(1L);
        Assert.assertFalse(afterComment.isPresent());
    }

    @Test
    @Transactional
    public void comment_delete_by_id_test(){
        commentRepository.deleteById(1L);
        Optional<Comment> afterComment = commentRepository.findById(1L);
        Assert.assertFalse(afterComment.isPresent());
    }

    @Test
    public void comment_exists_by_id_success_test(){
        Comment comment = commentRepository.getOne(1L);
        Assert.assertTrue(commentRepository.existsById(comment.getId()));
    }

    @Test
    public void comment_exists_by_id_failure_test(){
        Assert.assertFalse(commentRepository.existsById(0L));
    }

    @Test
    public void comment_count_test(){
        long count = commentRepository.count();
        List<Comment> comments = commentRepository.findAll();
        Assert.assertEquals(count, (long) comments.size());
    }

    @Test
    public void comment_count_by_request_test(){
        List<Comment> comments = commentRepository.findByRequestOrderByWrittenDateDesc(requestRepository.getOne(1L));
        long count = commentRepository.countByRequest(requestRepository.getOne(1L));
        Assert.assertEquals(count, (long) comments.size());
    }
}
