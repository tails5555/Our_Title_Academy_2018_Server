package io.kang.unit_test.domain_service_unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.kang.repository.redis.TodayRequestRepository;
import io.kang.service.domain_service.implement_object.TodayRequestServiceImpl;
import io.kang.service.domain_service.interfaces.TodayRequestService;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.TodayRequestTempSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TodayRequestDTOTempSingleton;
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

import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, RedisTestConfig.class})
public class TodayRequestServiceUnitTest {
    private MockMvc mockMvc;

    @InjectMocks
    private TodayRequestService todayRequestService = new TodayRequestServiceImpl();

    @Mock
    private TodayRequestRepository todayRequestRepository;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.todayRequestService).build();
    }

    @Test
    public void today_request_create_test() throws JsonProcessingException {
        doNothing().when(todayRequestRepository).create(TodayRequestTempSingleton.INSTANCE.getInstance());
        todayRequestService.create(TodayRequestDTOTempSingleton.INSTANCE.getInstance());
    }

    @Test
    public void today_request_find_all_test() {
        when(todayRequestRepository.findAll()).thenReturn(Arrays.asList(TodayRequestTempSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(todayRequestService.findAll(), Arrays.asList(TodayRequestDTOTempSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void today_request_find_by_last_test() throws IOException {
        when(todayRequestRepository.findByLast()).thenReturn(TodayRequestTempSingleton.INSTANCE.getInstance());
        Assert.assertEquals(todayRequestService.findByLast(), TodayRequestDTOTempSingleton.INSTANCE.getInstance());
    }

    @Test
    public void today_request_delete_by_first_test() {
        doNothing().when(todayRequestRepository).deleteByFirst();
        todayRequestService.deleteByFirst();
    }

    @Test
    public void today_request_count_test(){
        when(todayRequestRepository.count()).thenReturn(5L);
        Assert.assertEquals(todayRequestService.count(), 5L);
    }
}
