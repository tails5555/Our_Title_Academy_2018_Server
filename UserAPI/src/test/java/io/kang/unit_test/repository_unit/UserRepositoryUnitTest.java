package io.kang.unit_test.repository_unit;

import io.kang.domain.User;
import io.kang.enumeration.Type;
import io.kang.repository.UserRepository;
import io.kang.test_config.JpaTestConfig;
import io.kang.unit_test.singleton_object.domain_unit.UserCreateSingleton;
import io.kang.unit_test.singleton_object.domain_unit.UserUpdateSingleton;
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
public class UserRepositoryUnitTest {
    static final Logger logger = LoggerFactory.getLogger(AgeRepositoryUnitTest.class);

    @Autowired
    private UserRepository userRepository;

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
    public void user_find_all_test(){
        List<User> users = userRepository.findAll();
        Assert.assertTrue(users.size() != 0);
    }

    @Test
    public void user_get_one_success_test(){
        User user = userRepository.getOne(1L);
        Assert.assertTrue(user != null);
    }

    @Test(expected = LazyInitializationException.class)
    public void user_get_one_failure_test(){
        User user = userRepository.getOne(0L);
        user.toString();
    }

    @Test
    public void user_find_by_id_success_test(){
        Optional<User> tmpUser = userRepository.findById(1L);
        Assert.assertTrue(tmpUser.isPresent());
    }

    @Test
    public void user_find_by_id_failure_test(){
        Optional<User> tmpUser = userRepository.findById(0L);
        Assert.assertFalse(tmpUser.isPresent());
    }

    @Test
    public void user_find_by_user_type_test(){
        List<User> users = userRepository.findByUserType(Type.USER);
        Assert.assertTrue(users.size() > 0);
    }

    @Test
    public void user_find_by_nickname_contains_test(){
        List<User> users = userRepository.findByNicknameContains("관리자");
        Assert.assertTrue(users.size() > 0);
    }

    @Test
    public void user_find_by_login_id_test(){
        Optional<User> user = userRepository.findByLoginId("kang123");
        Assert.assertTrue(user.isPresent());
    }

    @Test
    @Transactional
    public void user_create_test(){
        User user = UserCreateSingleton.INSTANCE.getInstance();
        User createUser = userRepository.save(user);
        Assert.assertTrue(createUser.getId() != null);
    }

    @Test
    @Transactional
    public void user_update_test(){
        User user = userRepository.getOne(1L);
        User updateUser = userRepository.save(UserUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(user, updateUser);
    }

    @Test
    @Transactional
    public void user_delete_test(){
        User user = userRepository.getOne(1L);
        userRepository.delete(user);
        Optional<User> tmpUser = userRepository.findById(1L);
        Assert.assertFalse(tmpUser.isPresent());
    }

    @Test
    @Transactional
    public void user_delete_by_id_test(){
        userRepository.deleteById(1L);
        Optional<User> tmpUser = userRepository.findById(1L);
        Assert.assertFalse(tmpUser.isPresent());
    }

    @Test
    public void user_delete_by_login_id_test(){
        userRepository.deleteByLoginId("login_id");
        Optional<User> tmpUser = userRepository.findByLoginId("login_id");
        Assert.assertFalse(tmpUser.isPresent());
    }

    @Test
    public void user_exists_by_id_success_test(){
        User user = userRepository.getOne(1L);
        Assert.assertTrue(userRepository.existsById(user.getId()));
    }

    @Test
    public void user_exists_by_id_failure_test(){
        Assert.assertFalse(userRepository.existsById(0L));
    }

    @Test
    public void user_count_test(){
        long count = userRepository.count();
        List<User> users = userRepository.findAll();
        Assert.assertEquals(count, (long) users.size());
    }
}
