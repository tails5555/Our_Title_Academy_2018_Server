package io.kang.unit_test.repository_unit;

import io.kang.domain.mysql.Photo;
import io.kang.domain.mysql.Request;
import io.kang.repository.mysql.PhotoRepository;
import io.kang.repository.mysql.PhotoRepository;
import io.kang.repository.mysql.RequestRepository;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.PhotoCreateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.PhotoUpdateSingleton;
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
public class PhotoRepositoryUnitTest {
    static final Logger logger = LoggerFactory.getLogger(PhotoRepository.class);

    private static Random random = new Random();

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private PhotoRepository photoRepository;

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
    public void photo_find_all_test(){
        List<Photo> photos = photoRepository.findAll();
        Assert.assertTrue(photos.size() != 0);
    }

    @Test
    public void photo_get_one_success_test(){
        Random random = new Random();
        List<Photo> photos = photoRepository.findAll();
        Photo photo = photoRepository.getOne(photos.get(random.nextInt(photos.size())).getId());
        Assert.assertTrue(photo != null);
    }

    @Test(expected = LazyInitializationException.class)
    public void photo_get_one_failure_test(){
        Photo photo = photoRepository.getOne(0L);
        photo.toString();
    }

    @Test
    public void photo_find_by_id_success_test(){
        Random random = new Random();
        List<Photo> photos = photoRepository.findAll();
        Optional<Photo> tmpPhoto = photoRepository.findById(photos.get(random.nextInt(photos.size())).getId());
        Assert.assertTrue(tmpPhoto.isPresent());
    }

    @Test
    public void photo_find_by_id_failure_test(){
        Optional<Photo> tmpPhoto = photoRepository.findById(0L);
        Assert.assertFalse(tmpPhoto.isPresent());
    }

    @Test
    public void photo_find_by_request_success_test() {
        Request request = requestRepository.getOne(1L);
        Optional<Photo> tmpPhoto = photoRepository.findByRequest(request);
        Assert.assertTrue(tmpPhoto.isPresent());
    }

    @Test
    @Transactional
    public void photo_create_test(){
        Photo photo = PhotoCreateSingleton.INSTANCE.getInstance();
        photo.setUploadDate(LocalDateTime.now());
        Photo createPhoto = photoRepository.save(photo);
        Assert.assertTrue(createPhoto.getId() != null);
    }

    @Test
    @Transactional
    public void photo_update_test(){
        Photo photo = photoRepository.getOne(1L);
        Photo updatePhoto = photoRepository.save(PhotoUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(photo, updatePhoto);
    }

    @Test
    @Transactional
    public void photo_delete_test(){
        Photo photo = photoRepository.getOne(1L);
        photoRepository.delete(photo);
        Optional<Photo> afterPhoto = photoRepository.findById(1L);
        Assert.assertFalse(afterPhoto.isPresent());
    }

    @Test
    @Transactional
    public void photo_delete_by_id_test(){
        photoRepository.deleteById(1L);
        Optional<Photo> afterPhoto = photoRepository.findById(1L);
        Assert.assertFalse(afterPhoto.isPresent());
    }

    @Test
    public void photo_exists_by_id_success_test(){
        Photo photo = photoRepository.getOne(1L);
        Assert.assertTrue(photoRepository.existsById(photo.getId()));
    }

    @Test
    public void photo_exists_by_id_failure_test(){
        Assert.assertFalse(photoRepository.existsById(0L));
    }

    @Test
    public void photo_count_test(){
        long count = photoRepository.count();
        List<Photo> photos = photoRepository.findAll();
        Assert.assertEquals(count, (long) photos.size());
    }
}
