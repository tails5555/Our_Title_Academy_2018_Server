package io.kang.unit_test.domain_service_unit;

import io.kang.domain.mysql.RequestEmpathy;
import io.kang.dto.mysql.RequestDTO;
import io.kang.dto.mysql.RequestEmpathyDTO;
import io.kang.enumeration.Status;
import io.kang.repository.mysql.RequestEmpathyRepository;
import io.kang.service.domain_service.implement_object.RequestEmpathyServiceImpl;
import io.kang.service.domain_service.interfaces.EmpathyService;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.RequestEmpathyCreateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.RequestEmpathyUpdateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.RequestUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.RequestEmpathyDTOCreateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.RequestEmpathyDTOUpdateSingleton;
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
public class RequestEmpathyServiceUnitTest {
    private MockMvc mockMvc;

    @InjectMocks
    private EmpathyService<RequestEmpathyDTO, RequestDTO> requestEmpathyService = new RequestEmpathyServiceImpl();

    @Mock
    private RequestEmpathyRepository requestEmpathyRepository;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.requestEmpathyService).build();
    }

    @Test
    public void request_empathy_find_all_test(){
        when(requestEmpathyRepository.findAll()).thenReturn(Arrays.asList(RequestEmpathyUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(requestEmpathyService.findAll(), Arrays.asList(RequestEmpathyDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void request_empathy_get_one_success_test(){
        when(requestEmpathyRepository.getOne(1L)).thenReturn(RequestEmpathyUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(requestEmpathyService.getOne(1L), RequestEmpathyDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void request_empathy_get_one_failure_test(){
        when(requestEmpathyRepository.getOne(0L)).thenReturn(null);
        Assert.assertEquals(requestEmpathyService.getOne(0L), null);
    }

    @Test
    public void request_empathy_find_by_id_success_test(){
        when(requestEmpathyRepository.findById(1L)).thenReturn(Optional.of(RequestEmpathyUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(requestEmpathyService.findById(1L), RequestEmpathyDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void request_empathy_find_by_id_failure_test(){
        when(requestEmpathyRepository.findById(0L)).thenReturn(Optional.empty());
        Assert.assertEquals(requestEmpathyService.findById(0L), null);
    }

    @Test
    public void request_empathy_find_by_user_id_and_context_success_test(){
        when(requestEmpathyRepository.findByUserIdAndRequest("EMPATHY_USER_ID01", RequestUpdateSingleton.INSTANCE.getInstance())).thenReturn(Optional.of(RequestEmpathyUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(requestEmpathyService.findByUserIdAndContext("EMPATHY_USER_ID01", RequestDTOUpdateSingleton.INSTANCE.getInstance()), RequestEmpathyDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void request_empathy_find_by_user_id_and_context_failure_test(){
        when(requestEmpathyRepository.findByUserIdAndRequest("EMPATHY_USER_ID01", RequestUpdateSingleton.INSTANCE.getInstance())).thenReturn(Optional.empty());
        Assert.assertEquals(requestEmpathyService.findByUserIdAndContext("EMPATHY_USER_ID01", RequestDTOUpdateSingleton.INSTANCE.getInstance()), null);
    }

    @Test
    public void request_empathy_create_test(){
        RequestEmpathy createRequestEmpathy = RequestEmpathyCreateSingleton.INSTANCE.getInstance();
        RequestEmpathy afterRequestEmpathy = RequestEmpathyUpdateSingleton.INSTANCE.getInstance();
        when(requestEmpathyRepository.save(createRequestEmpathy)).thenReturn(afterRequestEmpathy);

        RequestEmpathyDTO requestEmpathyDTO = requestEmpathyService.create(RequestEmpathyDTOCreateSingleton.INSTANCE.getInstance());
        Assert.assertTrue(requestEmpathyDTO.getId() != null);

    }

    @Test
    public void request_empathy_update_test(){
        RequestEmpathy updateRequestEmpathy = RequestEmpathyUpdateSingleton.INSTANCE.getInstance();
        when(requestEmpathyRepository.save(updateRequestEmpathy)).thenReturn(updateRequestEmpathy);

        RequestEmpathyDTO requestEmpathyDTO = requestEmpathyService.update(RequestEmpathyDTOUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(requestEmpathyDTO, RequestEmpathyDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void request_empathy_delete_by_id_test(){
        doNothing().when(requestEmpathyRepository).deleteById(1L);
        requestEmpathyService.deleteById(1L);
    }

    @Test
    public void request_empathy_delete_by_user_id_and_context_test(){
        doNothing().when(requestEmpathyRepository).deleteByUserIdAndRequest("EMPATHY_USER_ID01", RequestUpdateSingleton.INSTANCE.getInstance());
        requestEmpathyService.deleteByUserIdAndContext("EMPATHY_USER_ID01", RequestDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void request_empathy_exists_by_id_success_test(){
        when(requestEmpathyRepository.existsById(1L)).thenReturn(true);
        Assert.assertTrue(requestEmpathyService.existsById(1L));
    }

    @Test
    public void request_empathy_exists_by_id_failure_test(){
        when(requestEmpathyRepository.existsById(1L)).thenReturn(false);
        Assert.assertFalse(requestEmpathyService.existsById(1L));
    }

    @Test
    public void request_empathy_exists_by_user_id_and_context_success_test(){
        when(requestEmpathyRepository.existsByUserIdAndRequest("EMPATHY_USER_ID01", RequestUpdateSingleton.INSTANCE.getInstance())).thenReturn(true);
        Assert.assertTrue(requestEmpathyService.existsByUserIdAndContext("EMPATHY_USER_ID01", RequestDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void request_empathy_exists_by_user_id_and_context_failure_test(){
        when(requestEmpathyRepository.existsByUserIdAndRequest("EMPATHY_USER_ID01", RequestUpdateSingleton.INSTANCE.getInstance())).thenReturn(false);
        Assert.assertFalse(requestEmpathyService.existsByUserIdAndContext("EMPATHY_USER_ID01", RequestDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void request_empathy_exists_by_user_id_and_context_and_status_success_test(){
        when(requestEmpathyRepository.existsByUserIdAndRequestAndStatus("EMPATHY_USER_ID01", RequestUpdateSingleton.INSTANCE.getInstance(), Status.LIKE)).thenReturn(true);
        Assert.assertTrue(requestEmpathyService.existsByUserIdAndContextAndStatus("EMPATHY_USER_ID01", RequestDTOUpdateSingleton.INSTANCE.getInstance(), Status.LIKE));
    }

    @Test
    public void request_empathy_exists_by_user_id_and_context_and_status_failure_test(){
        when(requestEmpathyRepository.existsByUserIdAndRequestAndStatus("EMPATHY_USER_ID01", RequestUpdateSingleton.INSTANCE.getInstance(), Status.LIKE)).thenReturn(false);
        Assert.assertFalse(requestEmpathyService.existsByUserIdAndContextAndStatus("EMPATHY_USER_ID01", RequestDTOUpdateSingleton.INSTANCE.getInstance(), Status.LIKE));
    }

    @Test
    public void request_empathy_count_test(){
        when(requestEmpathyRepository.count()).thenReturn(5L);
        Assert.assertEquals(requestEmpathyService.count(), 5L);
    }

    @Test
    public void request_empathy_count_by_context_and_status_test(){
        when(requestEmpathyRepository.countByRequestAndStatus(RequestUpdateSingleton.INSTANCE.getInstance(), Status.LIKE)).thenReturn(5L);
        Assert.assertEquals(requestEmpathyService.countByContextAndStatus(RequestDTOUpdateSingleton.INSTANCE.getInstance(), Status.LIKE), 5L);
    }
}
