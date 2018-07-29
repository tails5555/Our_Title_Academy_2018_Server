package io.kang.unit_test.service_unit.domain_service;

import io.kang.domain.City;
import io.kang.dto.CityDTO;
import io.kang.repository.CityRepository;
import io.kang.service.domain_service.implement_object.CityServiceImpl;
import io.kang.service.domain_service.interfaces.CityService;
import io.kang.test_config.JpaTestConfig;
import io.kang.unit_test.singleton_object.domain_unit.CityCreateSingleton;
import io.kang.unit_test.singleton_object.domain_unit.CityUpdateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.CityDTOCreateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.CityDTOUpdateSingleton;
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
        List<CityDTO> cityDTOs = cityService.findAll();
        Assert.assertEquals(cityDTOs, Arrays.asList(CityDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void city_get_one_success_test(){
        when(cityRepository.existsById(1L)).thenReturn(true);
        when(cityRepository.getOne(1L)).thenReturn(CityUpdateSingleton.INSTANCE.getInstance());
        CityDTO cityDTO = cityService.getOne(1L);
        Assert.assertEquals(cityDTO, CityDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void city_get_one_failure_test() {
        when(cityRepository.existsById(1L)).thenReturn(false);
        CityDTO cityDTO = cityService.getOne(1L);
        Assert.assertEquals(cityDTO, null);
    }

    @Test
    public void city_find_by_id_success_test(){
        when(cityRepository.findById(1L)).thenReturn(Optional.of(CityUpdateSingleton.INSTANCE.getInstance()));
        CityDTO cityDTO = cityService.findById(1L);
        Assert.assertEquals(cityDTO, CityDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void city_find_by_id_failure_test(){
        when(cityRepository.findById(1L)).thenReturn(Optional.empty());
        CityDTO cityDTO = cityService.findById(1L);
        Assert.assertEquals(cityDTO, null);
    }

    @Test
    public void city_create_test(){
        City createCity = CityCreateSingleton.INSTANCE.getInstance();
        City afterCity = CityUpdateSingleton.INSTANCE.getInstance();
        when(cityRepository.save(createCity)).thenReturn(afterCity);

        CityDTO createDTO = CityDTOCreateSingleton.INSTANCE.getInstance();
        CityDTO cityDTO = cityService.create(createDTO);

        Assert.assertEquals(cityDTO, CityDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void city_update_test(){
        City updateCity = CityUpdateSingleton.INSTANCE.getInstance();
        City afterCity = CityUpdateSingleton.INSTANCE.getInstance();
        when(cityRepository.save(updateCity)).thenReturn(afterCity);

        CityDTO updateDTO = CityDTOUpdateSingleton.INSTANCE.getInstance();
        CityDTO cityDTO = cityService.update(updateDTO);

        Assert.assertEquals(cityDTO, CityDTOUpdateSingleton.INSTANCE.getInstance());
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
