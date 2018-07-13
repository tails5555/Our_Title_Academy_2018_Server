package io.kang.unit_test.service_unit.domain_service;

import io.kang.domain.Profile;
import io.kang.repository.ProfileRepository;
import io.kang.service.domain_service.implement_object.ProfileServiceImpl;
import io.kang.service.domain_service.interfaces.ProfileService;
import io.kang.test_config.JpaTestConfig;
import io.kang.unit_test.singleton_object.repository_testing.ProfileCreateSingleton;
import io.kang.unit_test.singleton_object.repository_testing.ProfileUpdateSingleton;
import io.kang.unit_test.singleton_object.service_testing.ProfileVOCreateSingleton;
import io.kang.unit_test.singleton_object.service_testing.ProfileVOUpdateSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.ProfileVOSingleton;
import io.kang.vo.ProfileVO;
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
public class ProfileServiceUnitTest {
    private MockMvc mockMvc;

    @InjectMocks
    private ProfileService profileService = new ProfileServiceImpl();

    @Mock
    private ProfileRepository profileRepository;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.profileService).build();
    }

    @Test
    public void profile_find_all_test(){
        Profile profile = ProfileUpdateSingleton.INSTANCE.getInstance();
        when(profileRepository.findAll()).thenReturn(Arrays.asList(profile));
        List<ProfileVO> profileVOs = profileService.findAll();
        Assert.assertEquals(profileVOs, Arrays.asList(ProfileVO.builtToVO(profile)));
    }

    @Test
    public void profile_get_one_vo_success_test(){
        Profile profile = ProfileUpdateSingleton.INSTANCE.getInstance();
        when(profileRepository.existsById(1L)).thenReturn(true);
        when(profileRepository.getOne(1L)).thenReturn(profile);
        ProfileVO profileVO = profileService.getOneVO(1L);
        Assert.assertEquals(profileVO, ProfileVO.builtToVO(profile));
    }

    @Test
    public void profile_get_one_vo_failure_test() {
        when(profileRepository.existsById(1L)).thenReturn(false);
        ProfileVO profileVO = profileService.getOneVO(1L);
        Assert.assertEquals(profileVO, null);
    }

    @Test
    public void profile_find_by_id_vo_success_test(){
        Profile profile = ProfileUpdateSingleton.INSTANCE.getInstance();
        when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));
        ProfileVO profileVO = profileService.findByIdVO(1L);
        Assert.assertEquals(profileVO, ProfileVO.builtToVO(profile));
    }

    @Test
    public void profile_find_by_id_vo_failure_test(){
        when(profileRepository.findById(1L)).thenReturn(Optional.empty());
        ProfileVO profileVO = profileService.findByIdVO(1L);
        Assert.assertEquals(profileVO, null);
    }

    // update, create 테스트는 논리적인 오류가 있어서 잠깐 수정 뒤 이어 하겠습니다.

    @Test
    public void profile_delete_by_id_test(){
        doNothing().when(profileRepository).deleteById(1L);
        profileService.deleteById(1L);
    }

    @Test
    public void profile_exists_by_id_success_test(){
        when(profileRepository.existsById(1L)).thenReturn(true);
        boolean result = profileService.existsById(1L);
        Assert.assertTrue(result);
    }

    @Test
    public void profile_exists_by_id_failure_test(){
        when(profileRepository.existsById(1L)).thenReturn(false);
        boolean result = profileService.existsById(1L);
        Assert.assertFalse(result);
    }

    @Test
    public void profile_count_test(){
        when(profileRepository.count()).thenReturn(5L);
        long result = profileService.count();
        Assert.assertEquals(result, 5L);
    }
}
