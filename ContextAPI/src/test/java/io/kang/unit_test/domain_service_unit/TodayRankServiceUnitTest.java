package io.kang.unit_test.domain_service_unit;

import io.kang.domain.redis.TodayRank;
import io.kang.dto.redis.TodayRankDTO;
import io.kang.repository.redis.TodayRankRepository;
import io.kang.service.domain_service.implement_object.TodayRankServiceImpl;
import io.kang.service.domain_service.interfaces.TodayRankService;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.TodayRankCreateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.TodayRankUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TodayRankDTOCreateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TodayRankDTOUpdateSingleton;
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
public class TodayRankServiceUnitTest {
    private MockMvc mockMvc;
    
    @InjectMocks
    private TodayRankService todayRankService = new TodayRankServiceImpl();

    @Mock
    private TodayRankRepository todayRankRepository;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.todayRankService).build();
    }

    @Test
    public void today_rank_find_all_test(){
        when(todayRankRepository.findAll()).thenReturn(Arrays.asList(TodayRankUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(todayRankService.findAll(), Arrays.asList(TodayRankDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void today_rank_find_by_id_success_test(){
        when(todayRankRepository.findById(1L)).thenReturn(Optional.of(TodayRankUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(todayRankService.findById(1L), TodayRankDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void today_rank_find_by_id_failure_test(){
        when(todayRankRepository.findById(0L)).thenReturn(Optional.empty());
        Assert.assertEquals(todayRankService.findById(0L), null);
    }

    @Test
    public void today_rank_find_by_request_id_success_test(){
        when(todayRankRepository.findByRequestId(1L)).thenReturn(Optional.of(TodayRankUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(todayRankService.findByRequestId(1L), TodayRankDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void today_rank_find_by_request_id_failure_test(){
        when(todayRankRepository.findByRequestId(0L)).thenReturn(Optional.empty());
        Assert.assertEquals(todayRankService.findByRequestId(0L), null);
    }

    @Test
    public void today_rank_create_test(){
        TodayRank createTodayRank = TodayRankCreateSingleton.INSTANCE.getInstance();
        TodayRank afterTodayRank = TodayRankUpdateSingleton.INSTANCE.getInstance();
        when(todayRankRepository.save(createTodayRank)).thenReturn(afterTodayRank);

        TodayRankDTO todayRankDTO = todayRankService.create(TodayRankDTOCreateSingleton.INSTANCE.getInstance());
        Assert.assertTrue(todayRankDTO.getId() != null);
    }

    @Test
    public void today_rank_update_test(){
        TodayRank updateTodayRank = TodayRankUpdateSingleton.INSTANCE.getInstance();
        when(todayRankRepository.save(updateTodayRank)).thenReturn(updateTodayRank);

        TodayRankDTO todayRankDTO = todayRankService.update(TodayRankDTOUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(todayRankDTO, TodayRankDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void today_rank_delete_by_id_test(){
        doNothing().when(todayRankRepository).deleteById(1L);
        todayRankService.deleteById(1L);
    }

    @Test
    public void today_rank_delete_all_test(){
        doNothing().when(todayRankRepository).deleteAll();
        todayRankService.deleteAll();
    }

    @Test
    public void today_rank_exists_by_id_success_test(){
        when(todayRankRepository.existsById(1L)).thenReturn(true);
        Assert.assertTrue(todayRankService.existsById(1L));
    }

    @Test
    public void today_rank_exists_by_id_failure_test(){
        when(todayRankRepository.existsById(1L)).thenReturn(false);
        Assert.assertFalse(todayRankService.existsById(1L));
    }

    @Test
    public void today_rank_count_test(){
        when(todayRankRepository.count()).thenReturn(5L);
        Assert.assertEquals(todayRankService.count(), 5L);
    }
}
