package io.kang.unit_test.service_unit.domain_service;

import io.kang.domain.Profile;
import io.kang.dto.ProfileDTO;
import io.kang.repository.ProfileRepository;
import io.kang.service.domain_service.implement_object.ProfileServiceImpl;
import io.kang.service.domain_service.interfaces.ProfileService;
import io.kang.test_config.JpaTestConfig;
import io.kang.unit_test.singleton_object.domain_unit.ProfileCreateSingleton;
import io.kang.unit_test.singleton_object.domain_unit.ProfileUpdateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.ProfileDTOCreateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.ProfileDTOUpdateSingleton;
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
        List<ProfileDTO> profileDTOs = profileService.findAll();
        Assert.assertEquals(profileDTOs, Arrays.asList(ProfileDTO.builtToDTO(profile)));
    }

    @Test
    public void profile_get_one_success_test(){
        Profile profile = ProfileUpdateSingleton.INSTANCE.getInstance();
        when(profileRepository.existsById(1L)).thenReturn(true);
        when(profileRepository.getOne(1L)).thenReturn(profile);
        ProfileDTO profileDTO = profileService.getOne(1L);
        Assert.assertEquals(profileDTO, ProfileDTO.builtToDTO(profile));
    }

    @Test
    public void profile_get_one_failure_test() {
        when(profileRepository.existsById(1L)).thenReturn(false);
        ProfileDTO profileDTO = profileService.getOne(1L);
        Assert.assertEquals(profileDTO, null);
    }

    @Test
    public void profile_find_by_id_success_test(){
        Profile profile = ProfileUpdateSingleton.INSTANCE.getInstance();
        when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));
        ProfileDTO profileDTO = profileService.findById(1L);
        Assert.assertEquals(profileDTO, ProfileDTO.builtToDTO(profile));
    }

    @Test
    public void profile_find_by_id_failure_test(){
        when(profileRepository.findById(1L)).thenReturn(Optional.empty());
        ProfileDTO profileDTO = profileService.findById(1L);
        Assert.assertEquals(profileDTO, null);
    }

    @Test
    public void profile_find_by_user_login_id_success_test(){
        Profile profile = ProfileUpdateSingleton.INSTANCE.getInstance();
        when(profileRepository.findByUserLoginId("USER_LOGIN_ID01")).thenReturn(Optional.of(profile));
        ProfileDTO profileDTO = profileService.findByUserLoginId("USER_LOGIN_ID01");
        Assert.assertEquals(profileDTO, ProfileDTO.builtToDTO(profile));
    }

    @Test
    public void profile_find_by_user_login_id_failure_test(){
        when(profileRepository.findByUserLoginId("USER_LOGIN_ID01")).thenReturn(Optional.empty());
        ProfileDTO profileDTO = profileService.findByUserLoginId("USER_LOGIN_ID01");
        Assert.assertEquals(profileDTO, null);
    }

    @Test
    public void profile_create_test(){
        Profile createBefore = ProfileCreateSingleton.INSTANCE.getInstance();
        Profile createAfter = ProfileUpdateSingleton.INSTANCE.getInstance();
        when(profileRepository.save(createBefore)).thenReturn(createAfter);

        ProfileDTO createProfileDTO = profileService.create(ProfileDTOCreateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(createProfileDTO, ProfileDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void profile_update_test(){
        Profile updateBefore = ProfileUpdateSingleton.INSTANCE.getInstance();
        Profile updateAfter = ProfileUpdateSingleton.INSTANCE.getInstance();
        when(profileRepository.save(updateBefore)).thenReturn(updateAfter);

        ProfileDTO updateProfileDTO = profileService.update(ProfileDTOUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(updateProfileDTO, ProfileDTOUpdateSingleton.INSTANCE.getInstance());
    }

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
