package io.kang.unit_test.domain_service_unit;

import io.kang.domain.mysql.TitleEmpathy;
import io.kang.dto.mysql.TitleDTO;
import io.kang.dto.mysql.TitleEmpathyDTO;
import io.kang.enumeration.Status;
import io.kang.repository.mysql.TitleEmpathyRepository;
import io.kang.service.domain_service.implement_object.TitleEmpathyServiceImpl;
import io.kang.service.domain_service.interfaces.EmpathyService;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.TitleEmpathyCreateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.TitleEmpathyUpdateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.TitleUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TitleDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TitleEmpathyDTOCreateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TitleEmpathyDTOUpdateSingleton;
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
public class TitleEmpathyServiceUnitTest {
    private MockMvc mockMvc;

    @InjectMocks
    private EmpathyService<TitleEmpathyDTO, TitleDTO> titleEmpathyService = new TitleEmpathyServiceImpl();

    @Mock
    private TitleEmpathyRepository titleEmpathyRepository;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.titleEmpathyService).build();
    }

    @Test
    public void title_empathy_find_all_test(){
        when(titleEmpathyRepository.findAll()).thenReturn(Arrays.asList(TitleEmpathyUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(titleEmpathyService.findAll(), Arrays.asList(TitleEmpathyDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void title_empathy_get_one_success_test(){
        when(titleEmpathyRepository.getOne(1L)).thenReturn(TitleEmpathyUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(titleEmpathyService.getOne(1L), TitleEmpathyDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void title_empathy_get_one_failure_test(){
        when(titleEmpathyRepository.getOne(0L)).thenReturn(null);
        Assert.assertEquals(titleEmpathyService.getOne(0L), null);
    }

    @Test
    public void title_empathy_find_by_id_success_test(){
        when(titleEmpathyRepository.findById(1L)).thenReturn(Optional.of(TitleEmpathyUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(titleEmpathyService.findById(1L), TitleEmpathyDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void title_empathy_find_by_id_failure_test(){
        when(titleEmpathyRepository.findById(0L)).thenReturn(Optional.empty());
        Assert.assertEquals(titleEmpathyService.findById(0L), null);
    }

    @Test
    public void title_empathy_find_by_user_id_and_context_success_test(){
        when(titleEmpathyRepository.findByUserIdAndTitle("EMPATHY_USER_ID01", TitleUpdateSingleton.INSTANCE.getInstance())).thenReturn(Optional.of(TitleEmpathyUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(titleEmpathyService.findByUserIdAndContext("EMPATHY_USER_ID01", TitleDTOUpdateSingleton.INSTANCE.getInstance()), TitleEmpathyDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void title_empathy_find_by_user_id_and_context_failure_test(){
        when(titleEmpathyRepository.findByUserIdAndTitle("EMPATHY_USER_ID01", TitleUpdateSingleton.INSTANCE.getInstance())).thenReturn(Optional.empty());
        Assert.assertEquals(titleEmpathyService.findByUserIdAndContext("EMPATHY_USER_ID01", TitleDTOUpdateSingleton.INSTANCE.getInstance()), null);
    }

    @Test
    public void title_empathy_create_test(){
        TitleEmpathy createTitleEmpathy = TitleEmpathyCreateSingleton.INSTANCE.getInstance();
        TitleEmpathy afterTitleEmpathy = TitleEmpathyUpdateSingleton.INSTANCE.getInstance();
        when(titleEmpathyRepository.save(createTitleEmpathy)).thenReturn(afterTitleEmpathy);

        TitleEmpathyDTO titleEmpathyDTO = titleEmpathyService.create(TitleEmpathyDTOCreateSingleton.INSTANCE.getInstance());
        Assert.assertTrue(titleEmpathyDTO.getId() != null);
    }

    @Test
    public void title_empathy_update_test(){
        TitleEmpathy updateTitleEmpathy = TitleEmpathyUpdateSingleton.INSTANCE.getInstance();
        when(titleEmpathyRepository.save(updateTitleEmpathy)).thenReturn(updateTitleEmpathy);

        TitleEmpathyDTO titleEmpathyDTO = titleEmpathyService.update(TitleEmpathyDTOUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(titleEmpathyDTO, TitleEmpathyDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void title_empathy_delete_by_id_test(){
        doNothing().when(titleEmpathyRepository).deleteById(1L);
        titleEmpathyService.deleteById(1L);
    }

    @Test
    public void title_empathy_delete_by_user_id_and_context_test(){
        doNothing().when(titleEmpathyRepository).deleteByUserIdAndTitle("EMPATHY_USER_ID01", TitleUpdateSingleton.INSTANCE.getInstance());
        titleEmpathyService.deleteByUserIdAndContext("EMPATHY_USER_ID01", TitleDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void title_empathy_exists_by_id_success_test(){
        when(titleEmpathyRepository.existsById(1L)).thenReturn(true);
        Assert.assertTrue(titleEmpathyService.existsById(1L));
    }

    @Test
    public void title_empathy_exists_by_id_failure_test(){
        when(titleEmpathyRepository.existsById(1L)).thenReturn(false);
        Assert.assertFalse(titleEmpathyService.existsById(1L));
    }

    @Test
    public void title_empathy_exists_by_user_id_and_context_success_test(){
        when(titleEmpathyRepository.existsByUserIdAndTitle("EMPATHY_USER_ID01", TitleUpdateSingleton.INSTANCE.getInstance())).thenReturn(true);
        Assert.assertTrue(titleEmpathyService.existsByUserIdAndContext("EMPATHY_USER_ID01", TitleDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void title_empathy_exists_by_user_id_and_context_failure_test(){
        when(titleEmpathyRepository.existsByUserIdAndTitle("EMPATHY_USER_ID01", TitleUpdateSingleton.INSTANCE.getInstance())).thenReturn(false);
        Assert.assertFalse(titleEmpathyService.existsByUserIdAndContext("EMPATHY_USER_ID01", TitleDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void title_empathy_exists_by_user_id_and_context_and_status_success_test(){
        when(titleEmpathyRepository.existsByUserIdAndTitleAndStatus("EMPATHY_USER_ID01", TitleUpdateSingleton.INSTANCE.getInstance(), Status.LIKE)).thenReturn(true);
        Assert.assertTrue(titleEmpathyService.existsByUserIdAndContextAndStatus("EMPATHY_USER_ID01", TitleDTOUpdateSingleton.INSTANCE.getInstance(), Status.LIKE));
    }

    @Test
    public void title_empathy_exists_by_user_id_and_context_and_status_failure_test(){
        when(titleEmpathyRepository.existsByUserIdAndTitleAndStatus("EMPATHY_USER_ID01", TitleUpdateSingleton.INSTANCE.getInstance(), Status.LIKE)).thenReturn(false);
        Assert.assertFalse(titleEmpathyService.existsByUserIdAndContextAndStatus("EMPATHY_USER_ID01", TitleDTOUpdateSingleton.INSTANCE.getInstance(), Status.LIKE));
    }

    @Test
    public void title_empathy_count_test(){
        when(titleEmpathyRepository.count()).thenReturn(5L);
        Assert.assertEquals(titleEmpathyService.count(), 5L);
    }

    @Test
    public void title_empathy_count_by_context_and_status_test(){
        when(titleEmpathyRepository.countByTitleAndStatus(TitleUpdateSingleton.INSTANCE.getInstance(), Status.LIKE)).thenReturn(5L);
        Assert.assertEquals(titleEmpathyService.countByContextAndStatus(TitleDTOUpdateSingleton.INSTANCE.getInstance(), Status.LIKE), 5L);
    }
}
