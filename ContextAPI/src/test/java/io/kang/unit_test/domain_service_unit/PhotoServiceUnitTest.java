package io.kang.unit_test.domain_service_unit;

import io.kang.domain.mysql.Photo;
import io.kang.dto.mysql.PhotoDTO;
import io.kang.repository.mysql.PhotoRepository;
import io.kang.service.domain_service.implement_object.PhotoServiceImpl;
import io.kang.service.domain_service.interfaces.PhotoService;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.PhotoCreateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.PhotoUpdateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.RequestUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.PhotoDTOCreateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.PhotoDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, RedisTestConfig.class})
public class PhotoServiceUnitTest {
    private MockMvc mockMvc;

    @InjectMocks
    private PhotoService photoService = new PhotoServiceImpl();

    @Mock
    private PhotoRepository photoRepository;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.photoService).build();
    }

    @Test
    public void photo_find_all_test(){
        when(photoRepository.findAll()).thenReturn(Arrays.asList(PhotoUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(photoService.findAll(), Arrays.asList(PhotoDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void photo_get_one_success_test(){
        when(photoRepository.getOne(1L)).thenReturn(PhotoUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(photoService.getOne(1L), PhotoDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void photo_get_one_failure_test(){
        Assert.assertEquals(photoService.getOne(0L), null);
    }

    @Test
    public void photo_find_by_id_success_test(){
        when(photoRepository.findById(1L)).thenReturn(Optional.of(PhotoUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(photoService.findById(1L), PhotoDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void photo_find_by_id_failure_test(){
        when(photoRepository.findById(0L)).thenReturn(Optional.empty());
        Assert.assertEquals(photoService.findById(0L), null);
    }

    @Test
    public void photo_find_by_request_success_test(){
        when(photoRepository.findByRequest(RequestUpdateSingleton.INSTANCE.getInstance())).thenReturn(Optional.of(PhotoUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(photoService.findByRequestDTO(RequestDTOUpdateSingleton.INSTANCE.getInstance()), PhotoDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void photo_find_by_request_failure_test(){
        when(photoRepository.findByRequest(RequestUpdateSingleton.INSTANCE.getInstance())).thenReturn(Optional.empty());
        Assert.assertEquals(photoService.findByRequestDTO(RequestDTOUpdateSingleton.INSTANCE.getInstance()), null);
    }

    @Test
    public void photo_create_test(){
        Photo createPhoto = PhotoCreateSingleton.INSTANCE.getInstance();
        Photo afterPhoto = PhotoUpdateSingleton.INSTANCE.getInstance();
        when(photoRepository.save(createPhoto)).thenReturn(afterPhoto);

        PhotoDTO photoDTO = photoService.create(PhotoDTOCreateSingleton.INSTANCE.getInstance());
        Assert.assertTrue(photoDTO.getId() != null);
    }

    @Test
    public void photo_update_test(){
        Photo updatePhoto = PhotoUpdateSingleton.INSTANCE.getInstance();
        when(photoRepository.save(updatePhoto)).thenReturn(updatePhoto);

        PhotoDTO photoDTO = photoService.update(PhotoDTOUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(photoDTO, PhotoDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void photo_delete_by_id_test(){
        doNothing().when(photoRepository).deleteById(1L);
        photoService.deleteById(1L);
    }

    @Test
    public void photo_exists_by_id_success_test(){
        when(photoRepository.existsById(1L)).thenReturn(true);
        Assert.assertTrue(photoService.existsById(1L));
    }

    @Test
    public void photo_exists_by_id_failure_test(){
        when(photoRepository.existsById(1L)).thenReturn(false);
        Assert.assertFalse(photoService.existsById(1L));
    }

    @Test
    public void photo_count_test(){
        when(photoRepository.count()).thenReturn(5L);
        Assert.assertEquals(photoService.count(), 5L);
    }
}
