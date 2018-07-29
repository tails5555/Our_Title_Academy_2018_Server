package io.kang.unit_test.service_unit.domain_service;

import io.kang.domain.User;
import io.kang.dto.UserDTO;
import io.kang.enumeration.Type;
import io.kang.repository.UserRepository;
import io.kang.service.domain_service.implement_object.UserServiceImpl;
import io.kang.service.domain_service.interfaces.UserService;
import io.kang.test_config.JpaTestConfig;
import io.kang.unit_test.singleton_object.domain_unit.UserCreateSingleton;
import io.kang.unit_test.singleton_object.domain_unit.UserUpdateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.UserDTOCreateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.UserDTOUpdateSingleton;
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
public class UserServiceUnitTest {
    private MockMvc mockMvc;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Mock
    private UserRepository userRepository;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.userService).build();
    }

    @Test
    public void user_find_all_test(){
        User user = UserUpdateSingleton.INSTANCE.getInstance();
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        List<UserDTO> userDTOs = userService.findAll();
        Assert.assertEquals(userDTOs, Arrays.asList(UserDTO.builtToDTO(user)));
    }

    @Test
    public void user_find_by_user_type_test(){
        User user = UserUpdateSingleton.INSTANCE.getInstance();
        when(userRepository.findByUserType(Type.USER)).thenReturn(Arrays.asList(user));
        List<UserDTO> userDTOs = userService.findByUserType(Type.USER);
        Assert.assertEquals(userDTOs, Arrays.asList(UserDTO.builtToDTO(user)));
    }

    @Test
    public void user_find_by_nickname_contains_test(){
        User user = UserUpdateSingleton.INSTANCE.getInstance();
        when(userRepository.findByNicknameContains("NICKNAME")).thenReturn(Arrays.asList(user));
        List<UserDTO> userDTOs = userService.findByNicknameContains("NICKNAME");
        Assert.assertEquals(userDTOs, Arrays.asList(UserDTO.builtToDTO(user)));
    }

    @Test
    public void user_get_one_success_test(){
        User user = UserUpdateSingleton.INSTANCE.getInstance();
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.getOne(1L)).thenReturn(user);
        UserDTO userDTO = userService.getOne(1L);
        Assert.assertEquals(userDTO, UserDTO.builtToDTO(user));
    }

    @Test
    public void user_get_one_failure_test() {
        when(userRepository.existsById(1L)).thenReturn(false);
        UserDTO userDTO = userService.getOne(1L);
        Assert.assertEquals(userDTO, null);
    }

    @Test
    public void user_find_by_id_success_test(){
        User user = UserUpdateSingleton.INSTANCE.getInstance();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserDTO userDTO = userService.findById(1L);
        Assert.assertEquals(userDTO, UserDTO.builtToDTO(user));
    }

    @Test
    public void user_find_by_id_vo_failure_test(){
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        UserDTO userDTO = userService.findById(1L);
        Assert.assertEquals(userDTO, null);
    }

    @Test
    public void user_find_by_login_id_success_test(){
        when(userRepository.findByLoginId("USER_LOGIN_ID01")).thenReturn(Optional.of(UserUpdateSingleton.INSTANCE.getInstance()));
        UserDTO userDTO = userService.findByLoginId("USER_LOGIN_ID01");
        Assert.assertEquals(userDTO, UserDTO.builtToDTO(UserUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void user_find_by_login_id_failure_test(){
        when(userRepository.findByLoginId("LOGIN_ID")).thenReturn(Optional.empty());
        UserDTO userDTO = userService.findByLoginId("LOGIN_ID");
        Assert.assertEquals(userDTO, null);
    }

    @Test
    public void user_create_test(){
        User createBefore = UserCreateSingleton.INSTANCE.getInstance();
        User createAfter = UserUpdateSingleton.INSTANCE.getInstance();
        when(userRepository.save(createBefore)).thenReturn(createAfter);

        UserDTO createUserDTO = userService.create(UserDTOCreateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(createUserDTO, UserDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void user_update_test(){
        User updateBefore = UserUpdateSingleton.INSTANCE.getInstance();
        User updateAfter = UserUpdateSingleton.INSTANCE.getInstance();
        when(userRepository.save(updateBefore)).thenReturn(updateAfter);

        UserDTO updateUserDTO = userService.update(UserDTOUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(updateUserDTO, UserDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void user_delete_by_id_test(){
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteById(1L);
    }

    @Test
    public void user_delete_by_login_id_test(){
        doNothing().when(userRepository).deleteByLoginId("LOGIN_ID01");
        userService.deleteByLoginId("LOGIN_ID01");
    }

    @Test
    public void user_exists_by_id_success_test(){
        when(userRepository.existsById(1L)).thenReturn(true);
        boolean result = userService.existsById(1L);
        Assert.assertTrue(result);
    }

    @Test
    public void user_exists_by_id_failure_test(){
        when(userRepository.existsById(1L)).thenReturn(false);
        boolean result = userService.existsById(1L);
        Assert.assertFalse(result);
    }

    @Test
    public void user_count_test(){
        when(userRepository.count()).thenReturn(5L);
        long result = userService.count();
        Assert.assertEquals(result, 5L);
    }
}
