package io.kang.unit_test.domain_service_unit;

import io.kang.domain.mysql.Title;
import io.kang.dto.mysql.TitleDTO;
import io.kang.repository.mysql.TitleRepository;
import io.kang.service.domain_service.implement_object.TitleServiceImpl;
import io.kang.service.domain_service.interfaces.TitleService;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.CategoryUpdateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.RequestUpdateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.TitleCreateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.TitleUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.CategoryDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TitleDTOCreateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.TitleDTOUpdateSingleton;
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
public class TitleServiceUnitTest {
    private MockMvc mockMvc;

    @InjectMocks
    private TitleService titleService = new TitleServiceImpl();

    @Mock
    private TitleRepository titleRepository;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.titleService).build();
    }

    @Test
    public void title_find_all_test(){
        when(titleRepository.findAll()).thenReturn(Arrays.asList(TitleUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(titleService.findAll(), Arrays.asList(TitleDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void title_find_all_by_order_by_written_date_desc_test(){
        when(titleRepository.findAllByOrderByWrittenDateDesc()).thenReturn(Arrays.asList(TitleUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(titleService.findAllByOrderByWrittenDateDesc(), Arrays.asList(TitleDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void title_get_one_success_test(){
        when(titleRepository.getOne(1L)).thenReturn(TitleUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(titleService.getOne(1L), TitleDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void title_get_one_failure_test(){
        Assert.assertEquals(titleService.getOne(0L), null);
    }

    @Test
    public void title_find_by_id_success_test(){
        when(titleRepository.findById(1L)).thenReturn(Optional.of(TitleUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(titleService.findById(1L), TitleDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void title_find_by_id_failure_test(){
        when(titleRepository.findById(0L)).thenReturn(Optional.empty());
        Assert.assertEquals(titleService.findById(0L), null);
    }

    @Test
    public void title_find_by_user_id_order_by_written_date_desc_test(){
        when(titleRepository.findByUserIdOrderByWrittenDateDesc("TITLE_USER_ID01")).thenReturn(Arrays.asList(TitleUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(titleService.findByUserIdOrderByWrittenDateDesc("TITLE_USER_ID01"), Arrays.asList(TitleDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void title_find_by_user_id_and_request_category_test(){
        when(titleRepository.findByUserIdAndRequestCategory("TITLE_USER_ID01", CategoryUpdateSingleton.INSTANCE.getInstance())).thenReturn(Arrays.asList(TitleUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(titleService.findByUserIdAndRequestCategory("TITLE_USER_ID01", CategoryDTOUpdateSingleton.INSTANCE.getInstance()), Arrays.asList(TitleDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void title_find_by_request_order_by_written_date_desc_test(){
        when(titleRepository.findByRequestOrderByWrittenDateDesc(RequestUpdateSingleton.INSTANCE.getInstance())).thenReturn(Arrays.asList(TitleUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(titleService.findByRequestOrderByWrittenDateDesc(RequestDTOUpdateSingleton.INSTANCE.getInstance()), Arrays.asList(TitleDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void find_by_user_id_and_request_test(){
        when(titleRepository.findByUserIdAndRequest("TITLE_USER_ID01", RequestUpdateSingleton.INSTANCE.getInstance())).thenReturn(Optional.of(TitleUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(titleService.findByUserIdAndRequest("TITLE_USER_ID01", RequestDTOUpdateSingleton.INSTANCE.getInstance()), TitleDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void title_find_top_by_request_id_order_by_like_count_desc_test(){
        when(titleRepository.findTopByRequestIdOrderByLikeCountDesc(1L)).thenReturn(Optional.of(TitleUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(titleService.findTopByRequestIdOrderByLikeCountDesc(1L), TitleDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void title_find_top_five_by_request_id_order_by_like_count_desc_test(){
        when(titleRepository.findTop5ByRequestIdOrderByLikeCountDesc(1L)).thenReturn(Arrays.asList(TitleUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(titleService.findTop5ByRequestIdOrderByLikeCountDesc(1L), Arrays.asList(TitleDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void title_find_by_context_contains_test(){
        when(titleRepository.findByContextContains("TITLE_CONTEXT_01")).thenReturn(Arrays.asList(TitleUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(titleService.findByContextContains("TITLE_CONTEXT_01"), Arrays.asList(TitleDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void get_random_title_test(){
        when(titleRepository.findByRequestOrderByWrittenDateDesc(RequestUpdateSingleton.INSTANCE.getInstance())).thenReturn(Arrays.asList(TitleUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(titleService.getRandomTitle(RequestDTOUpdateSingleton.INSTANCE.getInstance()), TitleDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void title_create_test(){
        Title createTitle = TitleCreateSingleton.INSTANCE.getInstance();
        Title afterTitle = TitleUpdateSingleton.INSTANCE.getInstance();
        when(titleRepository.save(createTitle)).thenReturn(afterTitle);

        TitleDTO titleDTO = titleService.create(TitleDTOCreateSingleton.INSTANCE.getInstance());
        Assert.assertTrue(titleDTO.getId() != null);
    }

    @Test
    public void title_update_test(){
        Title updateTitle = TitleUpdateSingleton.INSTANCE.getInstance();
        when(titleRepository.save(updateTitle)).thenReturn(updateTitle);

        TitleDTO titleDTO = titleService.update(TitleDTOUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(titleDTO, TitleDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void title_delete_by_id_test(){
        doNothing().when(titleRepository).deleteById(1L);
        titleService.deleteById(1L);
    }

    @Test
    public void title_exists_by_id_success_test(){
        when(titleRepository.existsById(1L)).thenReturn(true);
        Assert.assertTrue(titleService.existsById(1L));
    }

    @Test
    public void title_exists_by_id_failure_test(){
        when(titleRepository.existsById(1L)).thenReturn(false);
        Assert.assertFalse(titleService.existsById(1L));
    }

    @Test
    public void title_count_test(){
        when(titleRepository.count()).thenReturn(5L);
        Assert.assertEquals(titleService.count(), 5L);
    }

    @Test
    public void title_count_by_request_test(){
        when(titleRepository.countByRequest(RequestUpdateSingleton.INSTANCE.getInstance())).thenReturn(5L);
        Assert.assertEquals(titleService.countByRequest(RequestDTOUpdateSingleton.INSTANCE.getInstance()), 5L);
    }
}
