package io.kang.unit_test.service_unit.domain_service;

import io.kang.domain.Age;
import io.kang.dto.AgeDTO;
import io.kang.repository.AgeRepository;
import io.kang.service.domain_service.implement_object.AgeServiceImpl;
import io.kang.service.domain_service.interfaces.AgeService;
import io.kang.test_config.JpaTestConfig;
import io.kang.unit_test.singleton_object.domain_unit.AgeCreateSingleton;
import io.kang.unit_test.singleton_object.domain_unit.AgeUpdateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.AgeDTOCreateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.AgeDTOUpdateSingleton;
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
        List<AgeDTO> ageDTOs = ageService.findAll();
        Assert.assertEquals(ageDTOs, Arrays.asList(AgeDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void age_get_one_success_test(){
        when(ageRepository.existsById(1L)).thenReturn(true);
        when(ageRepository.getOne(1L)).thenReturn(AgeUpdateSingleton.INSTANCE.getInstance());
        AgeDTO ageDTO = ageService.getOne(1L);
        Assert.assertEquals(ageDTO, AgeDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void age_get_one_failure_test() {
        when(ageRepository.existsById(1L)).thenReturn(false);
        AgeDTO ageDTO = ageService.getOne(1L);
        Assert.assertEquals(ageDTO, null);
    }

    @Test
    public void age_find_by_id_success_test(){
        when(ageRepository.findById(1L)).thenReturn(Optional.of(AgeUpdateSingleton.INSTANCE.getInstance()));
        AgeDTO ageDTO = ageService.findById(1L);
        Assert.assertEquals(ageDTO, AgeDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void age_find_by_id_failure_test(){
        when(ageRepository.findById(1L)).thenReturn(Optional.empty());
        AgeDTO ageDTO = ageService.findById(1L);
        Assert.assertEquals(ageDTO, null);
    }

    @Test
    public void age_create_test(){
        Age createAge = AgeCreateSingleton.INSTANCE.getInstance();
        Age afterAge = AgeUpdateSingleton.INSTANCE.getInstance();
        when(ageRepository.save(createAge)).thenReturn(afterAge);

        AgeDTO createDTO = AgeDTOCreateSingleton.INSTANCE.getInstance();
        AgeDTO ageDTO = ageService.create(createDTO);

        Assert.assertEquals(ageDTO, AgeDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void age_update_test(){
        Age updateAge = AgeUpdateSingleton.INSTANCE.getInstance();
        Age afterAge = AgeUpdateSingleton.INSTANCE.getInstance();
        when(ageRepository.save(updateAge)).thenReturn(afterAge);

        AgeDTO updateDTO = AgeDTOUpdateSingleton.INSTANCE.getInstance();
        AgeDTO ageDTO = ageService.update(updateDTO);

        Assert.assertEquals(ageDTO, AgeDTOUpdateSingleton.INSTANCE.getInstance());
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
