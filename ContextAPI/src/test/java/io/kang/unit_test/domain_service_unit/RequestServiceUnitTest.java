package io.kang.unit_test.domain_service_unit;

import io.kang.domain.mysql.Request;
import io.kang.dto.mysql.RequestDTO;
import io.kang.repository.mysql.RequestRepository;
import io.kang.service.domain_service.implement_object.RequestServiceImpl;
import io.kang.service.domain_service.interfaces.RequestService;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.CategoryUpdateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.RequestCreateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.RequestUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.CategoryDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOCreateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.RequestDTOUpdateSingleton;
import io.kang.unit_test.testing_singleton.model_unit.PaginationModelSingleton;
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
public class RequestServiceUnitTest {
    private MockMvc mockMvc;

    @InjectMocks
    private RequestService requestService = new RequestServiceImpl();

    @Mock
    private RequestRepository requestRepository;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.requestService).build();
    }

    @Test
    public void request_find_all_test(){
        when(requestRepository.findAll(PaginationModelSingleton.INSTANCE.getInstance())).thenReturn(Arrays.asList(RequestUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(requestService.findAll(PaginationModelSingleton.INSTANCE.getInstance()), Arrays.asList(RequestDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void request_get_one_success_test(){
        when(requestRepository.getOne(1L)).thenReturn(RequestUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(requestService.getOne(1L), RequestDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void request_get_one_failure_test(){
        Assert.assertEquals(requestService.getOne(0L), null);
    }

    @Test
    public void request_find_by_id_success_test(){
        when(requestRepository.findById(1L)).thenReturn(Optional.of(RequestUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(requestService.findById(1L), RequestDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void request_find_by_id_failure_test(){
        when(requestRepository.findById(0L)).thenReturn(Optional.empty());
        Assert.assertEquals(requestService.findById(0L), null);
    }

    @Test
    public void request_find_top_ten_by_category_is_not_null_and_available_is_true_order_by_view_desc_test(){
        when(requestRepository.findTop10ByCategoryIsNotNullAndAvailableIsTrueOrderByViewDesc()).thenReturn(Arrays.asList(RequestUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(requestService.findTop10ByCategoryIsNotNullAndAvailableIsTrueOrderByViewDesc(), Arrays.asList(RequestDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void request_find_top_ten_by_category_is_not_null_and_available_is_true_order_by_written_date_desc_test(){
        when(requestRepository.findTop10ByCategoryIsNotNullAndAvailableIsTrueOrderByWrittenDateDesc()).thenReturn(Arrays.asList(RequestUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(requestService.findTop10ByCategoryIsNotNullAndAvailableIsTrueOrderByWrittenDateDesc(), Arrays.asList(RequestDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void request_find_by_user_id_and_available_is_false_order_by_written_date_desc_test(){
        when(requestRepository.findByUserIdAndAvailableIsFalseOrderByWrittenDateDesc("REQUEST_USER_ID01")).thenReturn(Arrays.asList(RequestUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(requestService.findByUserIdAndAvailableIsFalseOrderByWrittenDateDesc("REQUEST_USER_ID01"), Arrays.asList(RequestDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void request_find_by_user_id_and_category_is_not_null_and_available_is_true_order_by_written_date_desc_test(){
        when(requestRepository.findByUserIdAndCategoryIsNotNullAndAvailableIsTrueOrderByWrittenDateDesc("REQUEST_USER_ID01")).thenReturn(Arrays.asList(RequestUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(requestService.findByUserIdAndCategoryIsNotNullAndAvailableIsTrueOrderByWrittenDateDesc("REQUEST_USER_ID01"), Arrays.asList(RequestDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void request_find_by_user_id_and_category_order_by_written_date_desc_test(){
        when(requestRepository.findByUserIdAndCategoryOrderByWrittenDateDesc("REQUEST_USER_ID01", CategoryUpdateSingleton.INSTANCE.getInstance())).thenReturn(Arrays.asList(RequestUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(requestService.findByUserIdAndCategoryOrderByWrittenDateDesc("REQUEST_USER_ID01", CategoryDTOUpdateSingleton.INSTANCE.getInstance()), Arrays.asList(RequestDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void request_find_by_category_is_not_null_and_available_is_true_test(){
        when(requestRepository.findByCategoryIsNotNullAndAvailableIsTrue()).thenReturn(Arrays.asList(RequestUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(requestService.findByCategoryIsNotNullAndAvailableIsTrue(), Arrays.asList(RequestDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void request_find_by_category_is_not_null_and_available_is_false_order_by_written_date_desc_test(){
        when(requestRepository.findByCategoryIsNotNullAndAvailableIsFalseOrderByWrittenDateDesc()).thenReturn(Arrays.asList(RequestUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(requestService.findByCategoryIsNotNullAndAvailableIsFalseOrderByWrittenDateDesc(), Arrays.asList(RequestDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void request_find_by_category_is_null_and_available_is_false_order_by_written_date_desc_test(){
        when(requestRepository.findByCategoryIsNullAndAvailableIsFalseOrderByWrittenDateDesc()).thenReturn(Arrays.asList(RequestUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(requestService.findByCategoryIsNullAndAvailableIsFalseOrderByWrittenDateDesc(), Arrays.asList(RequestDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void request_find_by_intro_contains_or_context_contains_test(){
        when(requestRepository.findByIntroContainsOrContextContains("REQUEST_INTRO_01", "REQUEST_INTRO_01")).thenReturn(Arrays.asList(RequestUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(requestService.findByIntroContainsOrContextContains("REQUEST_INTRO_01"), Arrays.asList(RequestDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void request_create_test(){
        Request createRequest = RequestCreateSingleton.INSTANCE.getInstance();
        Request afterRequest = RequestUpdateSingleton.INSTANCE.getInstance();
        when(requestRepository.save(createRequest)).thenReturn(afterRequest);

        RequestDTO requestDTO = requestService.create(RequestDTOCreateSingleton.INSTANCE.getInstance());
        Assert.assertTrue(requestDTO.getId() != null);
    }

    @Test
    public void request_update_test(){
        Request updateRequest = RequestUpdateSingleton.INSTANCE.getInstance();
        when(requestRepository.save(updateRequest)).thenReturn(updateRequest);

        RequestDTO requestDTO = requestService.update(RequestDTOUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(requestDTO, RequestDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void request_delete_by_id_test(){
        doNothing().when(requestRepository).deleteById(1L);
        requestService.deleteById(1L);
    }

    @Test
    public void request_exists_by_id_success_test(){
        when(requestRepository.existsById(1L)).thenReturn(true);
        Assert.assertTrue(requestService.existsById(1L));
    }

    @Test
    public void request_exists_by_id_failure_test(){
        when(requestRepository.existsById(1L)).thenReturn(false);
        Assert.assertFalse(requestService.existsById(1L));
    }

    @Test
    public void request_count_test(){
        when(requestRepository.count()).thenReturn(5L);
        Assert.assertEquals(requestService.count(), 5L);
    }
}
