package io.kang.unit_test.service_unit.domain_service;

import io.kang.domain.Detail;
import io.kang.repository.DetailRepository;
import io.kang.service.domain_service.implement_object.DetailServiceImpl;
import io.kang.service.domain_service.interfaces.DetailService;
import io.kang.test_config.JpaTestConfig;
import io.kang.unit_test.singleton_object.repository_testing.DetailCreateSingleton;
import io.kang.unit_test.singleton_object.repository_testing.DetailUpdateSingleton;
import io.kang.unit_test.singleton_object.service_testing.DetailVOCreateSingleton;
import io.kang.unit_test.singleton_object.service_testing.DetailVOUpdateSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.DetailVOSingleton;
import io.kang.vo.DetailVO;
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
public class DetailServiceUnitTest {
    private MockMvc mockMvc;

    @InjectMocks
    private DetailService detailService = new DetailServiceImpl();

    @Mock
    private DetailRepository detailRepository;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.detailService).build();
    }

    @Test
    public void detail_find_all_test(){
        Detail detail = DetailUpdateSingleton.INSTANCE.getInstance();
        when(detailRepository.findAll()).thenReturn(Arrays.asList(detail));
        List<DetailVO> detailVOs = detailService.findAll();
        Assert.assertEquals(detailVOs, Arrays.asList(DetailVO.builtToVO(detail)));
    }

    @Test
    public void detail_get_one_vo_success_test(){
        Detail detail = DetailUpdateSingleton.INSTANCE.getInstance();
        when(detailRepository.existsById(1L)).thenReturn(true);
        when(detailRepository.getOne(1L)).thenReturn(detail);
        DetailVO detailVO = detailService.getOneVO(1L);
        Assert.assertEquals(detailVO, DetailVO.builtToVO(detail));
    }

    @Test
    public void detail_get_one_vo_failure_test() {
        when(detailRepository.existsById(1L)).thenReturn(false);
        DetailVO detailVO = detailService.getOneVO(1L);
        Assert.assertEquals(detailVO, null);
    }

    @Test
    public void detail_find_by_id_vo_success_test(){
        Detail detail = DetailUpdateSingleton.INSTANCE.getInstance();
        when(detailRepository.findById(1L)).thenReturn(Optional.of(detail));
        DetailVO detailVO = detailService.findByIdVO(1L);
        Assert.assertEquals(detailVO, DetailVO.builtToVO(detail));
    }

    @Test
    public void detail_find_by_id_vo_failure_test(){
        when(detailRepository.findById(1L)).thenReturn(Optional.empty());
        DetailVO detailVO = detailService.findByIdVO(1L);
        Assert.assertEquals(detailVO, null);
    }

    @Test
    public void detail_find_by_user_login_id_success_test(){
        when(detailRepository.findByUserLoginId("USER_LOGIN_ID01")).thenReturn(Optional.of(DetailUpdateSingleton.INSTANCE.getInstance()));
        DetailVO detailVO = detailService.findByLoginIdVO("USER_LOGIN_ID01");
        Assert.assertEquals(detailVO, DetailVOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void detail_find_by_user_login_id_failure_test(){
        when(detailRepository.findByUserLoginId("LOGIN_ID")).thenReturn(Optional.empty());
        DetailVO detailVO = detailService.findByLoginIdVO("LOGIN_ID");
        Assert.assertEquals(detailVO, null);
    }
    @Test
    public void detail_create_test(){
        Detail createBefore = DetailCreateSingleton.INSTANCE.getInstance();
        Detail createAfter = DetailUpdateSingleton.INSTANCE.getInstance();
        when(detailRepository.save(createBefore)).thenReturn(createAfter);

        DetailVO createDetailVO = detailService.create(DetailVOCreateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(createDetailVO, DetailVOSingleton.INSTANCE.getInstance());
    }

    @Test
    public void detail_update_test(){
        Detail updateBefore = DetailUpdateSingleton.INSTANCE.getInstance();
        Detail updateAfter = DetailUpdateSingleton.INSTANCE.getInstance();
        when(detailRepository.save(updateBefore)).thenReturn(updateAfter);

        DetailVO updateDetailVO = detailService.update(DetailVOUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(updateDetailVO, DetailVOSingleton.INSTANCE.getInstance());
    }

    @Test
    public void detail_delete_by_id_test(){
        doNothing().when(detailRepository).deleteById(1L);
        detailService.deleteById(1L);
    }

    @Test
    public void detail_exists_by_id_success_test(){
        when(detailRepository.existsById(1L)).thenReturn(true);
        boolean result = detailService.existsById(1L);
        Assert.assertTrue(result);
    }

    @Test
    public void detail_exists_by_id_failure_test(){
        when(detailRepository.existsById(1L)).thenReturn(false);
        boolean result = detailService.existsById(1L);
        Assert.assertFalse(result);
    }

    @Test
    public void detail_count_test(){
        when(detailRepository.count()).thenReturn(5L);
        long result = detailService.count();
        Assert.assertEquals(result, 5L);
    }
}
