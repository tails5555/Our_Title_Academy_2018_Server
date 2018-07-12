package io.kang.unit_test.service_unit.domain_service;

import io.kang.domain.City;
import io.kang.model.CityModel;
import io.kang.repository.CityRepository;
import io.kang.service.domain_service.implement_object.CityServiceImpl;
import io.kang.service.domain_service.interfaces.CityService;
import io.kang.test_config.JpaTestConfig;
import io.kang.unit_test.singleton_object.model_testing.CityModelSingleton;
import io.kang.unit_test.singleton_object.repository_testing.CityCreateSingleton;
import io.kang.unit_test.singleton_object.repository_testing.CityUpdateSingleton;
import io.kang.unit_test.singleton_object.service_testing.CityModelCreateSingleton;
import io.kang.unit_test.singleton_object.service_testing.CityModelUpdateSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.CityVOSingleton;
import io.kang.vo.CityVO;
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
public class CityServiceUnitTest {
    private MockMvc mockMvc;

    @InjectMocks
    private CityService cityService = new CityServiceImpl();

    @Mock
    private CityRepository cityRepository;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.cityService).build();
    }

    @Test
    public void city_find_all_test(){
        when(cityRepository.findAll()).thenReturn(Arrays.asList(CityUpdateSingleton.INSTANCE.getInstance()));
        List<CityVO> cityVOs = cityService.findAll();
        Assert.assertEquals(cityVOs, Arrays.asList(CityVOSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void city_get_one_vo_success_test(){
        when(cityRepository.existsById(1L)).thenReturn(true);
        when(cityRepository.getOne(1L)).thenReturn(CityUpdateSingleton.INSTANCE.getInstance());
        CityVO cityVO = cityService.getOneVO(1L);
        Assert.assertEquals(cityVO, CityVOSingleton.INSTANCE.getInstance());
    }

    @Test
    public void city_get_one_vo_failure_test() {
        when(cityRepository.existsById(1L)).thenReturn(false);
        CityVO cityVO = cityService.getOneVO(1L);
        Assert.assertEquals(cityVO, null);
    }

    @Test
    public void city_get_one_model_success_test(){
        when(cityRepository.existsById(1L)).thenReturn(true);
        when(cityRepository.getOne(1L)).thenReturn(CityUpdateSingleton.INSTANCE.getInstance());
        CityModel cityModel = cityService.getOneModel(1L);
        Assert.assertEquals(cityModel, CityModelSingleton.INSTANCE.getInstance());
    }

    @Test
    public void city_get_one_model_failure_test(){
        when(cityRepository.existsById(1L)).thenReturn(false);
        CityModel cityModel = cityService.getOneModel(1L);
        Assert.assertEquals(cityModel, null);
    }

    @Test
    public void city_find_by_id_vo_success_test(){
        when(cityRepository.findById(1L)).thenReturn(Optional.of(CityUpdateSingleton.INSTANCE.getInstance()));
        CityVO cityVO = cityService.findByIdVO(1L);
        Assert.assertEquals(cityVO, CityVOSingleton.INSTANCE.getInstance());
    }

    @Test
    public void city_find_by_id_vo_failure_test(){
        when(cityRepository.findById(1L)).thenReturn(Optional.empty());
        CityVO cityVO = cityService.findByIdVO(1L);
        Assert.assertEquals(cityVO, null);
    }

    @Test
    public void city_find_by_id_model_success_test(){
        when(cityRepository.findById(1L)).thenReturn(Optional.of(CityUpdateSingleton.INSTANCE.getInstance()));
        CityModel cityModel = cityService.findByIdModel(1L);
        Assert.assertEquals(cityModel, CityModelSingleton.INSTANCE.getInstance());
    }

    @Test
    public void city_find_by_id_model_failure_test(){
        when(cityRepository.findById(1L)).thenReturn(Optional.empty());
        CityModel cityModel = cityService.findByIdModel(1L);
        Assert.assertEquals(cityModel, null);
    }

    @Test
    public void city_create_test(){
        City createCity = CityCreateSingleton.INSTANCE.getInstance();
        City afterCity = CityUpdateSingleton.INSTANCE.getInstance();
        when(cityRepository.save(createCity)).thenReturn(afterCity);

        CityModel cityModel = CityModelCreateSingleton.INSTANCE.getInstance();
        CityVO cityVO = cityService.create(cityModel);

        Assert.assertEquals(cityVO, CityVOSingleton.INSTANCE.getInstance());
    }

    @Test
    public void city_update_test(){
        City updateCity = CityUpdateSingleton.INSTANCE.getInstance();
        City afterCity = CityUpdateSingleton.INSTANCE.getInstance();
        when(cityRepository.save(updateCity)).thenReturn(afterCity);

        CityModel cityModel = CityModelUpdateSingleton.INSTANCE.getInstance();
        CityVO cityVO = cityService.update(cityModel);

        Assert.assertEquals(cityVO, CityVOSingleton.INSTANCE.getInstance());
    }

    @Test
    public void city_delete_by_id_test(){
        doNothing().when(cityRepository).deleteById(1L);
        cityService.deleteById(1L);
    }

    @Test
    public void city_exists_by_id_success_test(){
        when(cityRepository.existsById(1L)).thenReturn(true);
        boolean result = cityService.existsById(1L);
        Assert.assertTrue(result);
    }

    @Test
    public void city_exists_by_id_failure_test(){
        when(cityRepository.existsById(1L)).thenReturn(false);
        boolean result = cityService.existsById(1L);
        Assert.assertFalse(result);
    }

    @Test
    public void city_count_test(){
        when(cityRepository.count()).thenReturn(5L);
        long result = cityService.count();
        Assert.assertEquals(result, 5L);
    }
}
