package io.kang.unit_test.service_unit.domain_service;

import io.kang.domain.Age;
import io.kang.model.AgeModel;
import io.kang.repository.AgeRepository;
import io.kang.service.domain_service.implement_object.AgeServiceImpl;
import io.kang.service.domain_service.interfaces.AgeService;
import io.kang.test_config.JpaTestConfig;
import io.kang.unit_test.singleton_object.model_testing.AgeModelSingleton;
import io.kang.unit_test.singleton_object.repository_testing.AgeCreateSingleton;
import io.kang.unit_test.singleton_object.repository_testing.AgeUpdateSingleton;
import io.kang.unit_test.singleton_object.service_testing.AgeModelCreateSingleton;
import io.kang.unit_test.singleton_object.service_testing.AgeModelUpdateSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.AgeVOSingleton;
import io.kang.vo.AgeVO;
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
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = JpaTestConfig.class)
public class AgeServiceUnitTest {
    private MockMvc mockMvc;

    @InjectMocks
    private AgeService ageService = new AgeServiceImpl();

    @Mock
    private AgeRepository ageRepository;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.ageService).build();
    }

    @Test
    public void age_find_all_test(){
        when(ageRepository.findAll()).thenReturn(Arrays.asList(AgeUpdateSingleton.INSTANCE.getInstance()));
        List<AgeVO> ageVOs = ageService.findAll();
        Assert.assertEquals(ageVOs, Arrays.asList(AgeVOSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void age_get_one_vo_success_test(){
        when(ageRepository.existsById(1L)).thenReturn(true);
        when(ageRepository.getOne(1L)).thenReturn(AgeUpdateSingleton.INSTANCE.getInstance());
        AgeVO ageVO = ageService.getOneVO(1L);
        Assert.assertEquals(ageVO, AgeVOSingleton.INSTANCE.getInstance());
    }

    @Test
    public void age_get_one_vo_failure_test() {
        when(ageRepository.existsById(1L)).thenReturn(false);
        AgeVO ageVO = ageService.getOneVO(1L);
        Assert.assertEquals(ageVO, null);
    }

    @Test
    public void age_get_one_model_success_test(){
        when(ageRepository.existsById(1L)).thenReturn(true);
        when(ageRepository.getOne(1L)).thenReturn(AgeUpdateSingleton.INSTANCE.getInstance());
        AgeModel ageModel = ageService.getOneModel(1L);
        Assert.assertEquals(ageModel, AgeModelSingleton.INSTANCE.getInstance());
    }

    @Test
    public void age_get_one_model_failure_test(){
        when(ageRepository.existsById(1L)).thenReturn(false);
        AgeModel ageModel = ageService.getOneModel(1L);
        Assert.assertEquals(ageModel, null);
    }

    @Test
    public void age_find_by_id_vo_success_test(){
        when(ageRepository.findById(1L)).thenReturn(Optional.of(AgeUpdateSingleton.INSTANCE.getInstance()));
        AgeVO ageVO = ageService.findByIdVO(1L);
        Assert.assertEquals(ageVO, AgeVOSingleton.INSTANCE.getInstance());
    }

    @Test
    public void age_find_by_id_vo_failure_test(){
        when(ageRepository.findById(1L)).thenReturn(Optional.empty());
        AgeVO ageVO = ageService.findByIdVO(1L);
        Assert.assertEquals(ageVO, null);
    }

    @Test
    public void age_find_by_id_model_success_test(){
        when(ageRepository.findById(1L)).thenReturn(Optional.of(AgeUpdateSingleton.INSTANCE.getInstance()));
        AgeModel ageModel = ageService.findByIdModel(1L);
        Assert.assertEquals(ageModel, AgeModelSingleton.INSTANCE.getInstance());
    }

    @Test
    public void age_find_by_id_model_failure_test(){
        when(ageRepository.findById(1L)).thenReturn(Optional.empty());
        AgeModel ageModel = ageService.findByIdModel(1L);
        Assert.assertEquals(ageModel, null);
    }

    @Test
    public void age_create_test(){
        Age createAge = AgeCreateSingleton.INSTANCE.getInstance();
        Age afterAge = AgeUpdateSingleton.INSTANCE.getInstance();
        when(ageRepository.save(createAge)).thenReturn(afterAge);

        AgeModel ageModel = AgeModelCreateSingleton.INSTANCE.getInstance();
        AgeVO ageVO = ageService.create(ageModel);

        Assert.assertEquals(ageVO, AgeVOSingleton.INSTANCE.getInstance());
    }

    @Test
    public void age_update_test(){
        Age updateAge = AgeUpdateSingleton.INSTANCE.getInstance();
        Age afterAge = AgeUpdateSingleton.INSTANCE.getInstance();
        when(ageRepository.save(updateAge)).thenReturn(afterAge);

        AgeModel ageModel = AgeModelUpdateSingleton.INSTANCE.getInstance();
        AgeVO ageVO = ageService.update(ageModel);

        Assert.assertEquals(ageVO, AgeVOSingleton.INSTANCE.getInstance());
    }

    @Test
    public void age_delete_by_id_test(){
        doNothing().when(ageRepository).deleteById(1L);
        ageService.deleteById(1L);
    }

    @Test
    public void age_exists_by_id_success_test(){
        when(ageRepository.existsById(1L)).thenReturn(true);
        boolean result = ageService.existsById(1L);
        Assert.assertTrue(result);
    }

    @Test
    public void age_exists_by_id_failure_test(){
        when(ageRepository.existsById(1L)).thenReturn(false);
        boolean result = ageService.existsById(1L);
        Assert.assertFalse(result);
    }

    @Test
    public void age_count_test(){
        when(ageRepository.count()).thenReturn(5L);
        long result = ageService.count();
        Assert.assertEquals(result, 5L);
    }
}
