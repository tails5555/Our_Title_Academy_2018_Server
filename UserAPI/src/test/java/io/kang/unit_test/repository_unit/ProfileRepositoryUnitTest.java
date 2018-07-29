package io.kang.unit_test.repository_unit;

import io.kang.domain.Profile;
import io.kang.repository.ProfileRepository;
import io.kang.test_config.JpaTestConfig;
import io.kang.unit_test.singleton_object.domain_unit.ProfileCreateSingleton;
import io.kang.unit_test.singleton_object.domain_unit.ProfileUpdateSingleton;
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
public class ProfileRepositoryUnitTest {
    static final Logger logger = LoggerFactory.getLogger(AgeRepositoryUnitTest.class);

    @Autowired
    private ProfileRepository profileRepository;

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
    public void profile_find_all_test(){
        List<Profile> profiles = profileRepository.findAll();
        Assert.assertTrue(profiles.size() != 0);
    }

    @Test
    public void profile_get_one_success_test(){
        Profile profile = profileRepository.getOne(1L);
        Assert.assertTrue(profile != null);
    }

    @Test(expected = LazyInitializationException.class)
    public void profile_get_one_failure_test(){
        Profile profile = profileRepository.getOne(0L);
        profile.toString();
    }

    @Test
    public void profile_find_by_id_success_test(){
        Optional<Profile> tmpProfile = profileRepository.findById(1L);
        Assert.assertTrue(tmpProfile.isPresent());
    }

    @Test
    public void profile_find_by_id_failure_test(){
        Optional<Profile> tmpProfile = profileRepository.findById(0L);
        Assert.assertFalse(tmpProfile.isPresent());
    }

    @Test
    public void profile_find_by_user_login_id_success_test(){
        Optional<Profile> tmpProfile = profileRepository.findByUserLoginId("kang123");
        Assert.assertTrue(tmpProfile.isPresent());
    }

    @Test
    public void profile_find_by_user_login_id_failure_test(){
        Optional<Profile> tmpProfile = profileRepository.findByUserLoginId("login_id");
        Assert.assertFalse(tmpProfile.isPresent());
    }

    @Test
    @Transactional
    public void profile_create_test(){
        Profile profile = ProfileCreateSingleton.INSTANCE.getInstance();
        Profile createProfile = profileRepository.save(profile);
        Assert.assertTrue(createProfile.getId() != null);
    }

    @Test
    @Transactional
    public void profile_update_test(){
        Profile profile = profileRepository.getOne(1L);
        Profile updateProfile = profileRepository.save(ProfileUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(profile, updateProfile);
    }

    @Test
    @Transactional
    public void profile_delete_test(){
        Profile profile = profileRepository.getOne(1L);
        profileRepository.delete(profile);
        Optional<Profile> tmpProfile = profileRepository.findById(1L);
        Assert.assertFalse(tmpProfile.isPresent());
    }

    @Test
    @Transactional
    public void profile_delete_by_id_test(){
        profileRepository.deleteById(1L);
        Optional<Profile> tmpProfile = profileRepository.findById(1L);
        Assert.assertFalse(tmpProfile.isPresent());
    }

    @Test
    public void profile_exists_by_id_success_test(){
        Profile profile = profileRepository.getOne(1L);
        Assert.assertTrue(profileRepository.existsById(profile.getId()));
    }

    @Test
    public void profile_exists_by_id_failure_test(){
        Assert.assertFalse(profileRepository.existsById(0L));
    }

    @Test
    public void profile_count_test(){
        long count = profileRepository.count();
        List<Profile> profiles = profileRepository.findAll();
        Assert.assertEquals(count, (long) profiles.size());
    }
}
