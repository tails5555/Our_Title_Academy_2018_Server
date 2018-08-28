package io.kang.unit_test.domain_service_unit;

import io.kang.repository.redis.BattleTitleRepository;
import io.kang.service.domain_service.implement_object.BattleTitleServiceImpl;
import io.kang.service.domain_service.interfaces.BattleTitleService;
import io.kang.test_config.JpaTestConfig;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = JpaTestConfig.class)
public class BattleTitleServiceUnitTest {
    private MockMvc mockMvc;

    @InjectMocks
    private BattleTitleService battleTitleService = new BattleTitleServiceImpl();

    @Mock
    private BattleTitleRepository battleTitleRepository;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.battleTitleService).build();
    }

    @Test
    public void battle_title_create_test() {
        doNothing().when(battleTitleRepository).create(1L);
        battleTitleService.create(1L);
    }


    @Test
    public void battle_title_find_all_test() {
        when(battleTitleRepository.findAll()).thenReturn(Arrays.asList(1L, 2L, 3L));
        Assert.assertEquals(battleTitleService.findAll(), Arrays.asList(1L, 2L, 3L));
    }

    @Test
    public void battle_title_delete_all_test() {
        doNothing().when(battleTitleRepository).deleteAll();
        battleTitleService.deleteAll();
    }

    @Test
    public void battle_title_delete_by_title_id_test() {
        doNothing().when(battleTitleRepository).deleteByTitleId(1L);
        battleTitleService.deleteByTitleId(1L);
    }

    @Test
    public void battle_title_count_test(){
        when(battleTitleRepository.count()).thenReturn(5L);
        Assert.assertEquals(battleTitleService.count(), 5L);
    }
}
