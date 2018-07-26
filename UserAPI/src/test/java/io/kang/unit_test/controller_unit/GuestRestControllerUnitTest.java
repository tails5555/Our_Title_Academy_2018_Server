package io.kang.unit_test.controller_unit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import io.kang.exception.CustomException;
import io.kang.rest_controller.GuestRestController;
import io.kang.service.domain_service.implement_object.AgeServiceImpl;
import io.kang.service.domain_service.implement_object.CityServiceImpl;
import io.kang.service.domain_service.implement_object.UserServiceImpl;
import io.kang.service.domain_service.interfaces.AgeService;
import io.kang.service.domain_service.interfaces.CityService;
import io.kang.service.domain_service.interfaces.UserService;
import io.kang.service.integrate_service.implement_object.AccountServiceImpl;
import io.kang.service.integrate_service.implement_object.TokenLoginServiceImpl;
import io.kang.service.integrate_service.interfaces.AccountService;
import io.kang.service.integrate_service.interfaces.TokenLoginService;
import io.kang.unit_test.singleton_object.model_testing.FindModelSingleton;
import io.kang.unit_test.singleton_object.model_testing.LoginModelSingleton;
import io.kang.unit_test.singleton_object.model_testing.SignModelSingleton;
import io.kang.unit_test.singleton_object.service_testing.DetailVOUpdateSingleton;
import io.kang.unit_test.singleton_object.service_testing.UserVOUpdateSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.AgeVOSingleton;
import io.kang.unit_test.singleton_object.value_object_testing.CityVOSingleton;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.Filter;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {io.kang.security_config.SecurityConfiguration.class})
@WebAppConfiguration
@SpringBootTest(classes = {io.kang.UserApiApplication.class})
public class GuestRestControllerUnitTest {
    private MockMvc mockMvc;

    @Mock
    private AgeService ageService = new AgeServiceImpl();

    @Mock
    private CityService cityService = new CityServiceImpl();

    @Mock
    private UserService userService = new UserServiceImpl();

    @Mock
    private TokenLoginService tokenLoginService = new TokenLoginServiceImpl();

    @Mock
    private AccountService accountService = new AccountServiceImpl();

    @InjectMocks
    private GuestRestController guestRestController;

    @Autowired
    private Filter springSecurityFilterChain;

    private String jsonStringFromObject(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .modules(new JSR310Module())
                .build();
        return objectMapper.writeValueAsString(object);
    }

    @Before
    public void set_up() throws Exception{
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(guestRestController)
                .addFilters()
                .alwaysDo(print())
                .apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain))
                .build();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void guest_login_success_test() throws Exception {
        when(tokenLoginService.tokenLogin("LOGIN_ID01", "LOGIN_PASSWORD01")).thenReturn("TMP_TOKEN");
        mockMvc
                .perform(post("/UserAPI/auth/guest/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(this.jsonStringFromObject(LoginModelSingleton.INSTANCE.getInstance()))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void guest_login_failure_test() throws Exception {
        when(tokenLoginService.tokenLogin("LOGIN_ID01", "LOGIN_PASSWORD01")).thenThrow(new CustomException("CustomException_203", HttpStatus.NON_AUTHORITATIVE_INFORMATION));
        mockMvc
                .perform(post("/UserAPI/auth/guest/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonStringFromObject(LoginModelSingleton.INSTANCE.getInstance()))
                )
                .andExpect(status().isNonAuthoritativeInformation());
    }

    @Test
    public void guest_sign_up_success_test() throws Exception {
        when(accountService.executeSignUp(SignModelSingleton.INSTANCE.getInstance())).thenReturn(DetailVOUpdateSingleton.INSTANCE.getInstance());
        mockMvc
                .perform(post("/UserAPI/auth/guest/sign_up")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(this.jsonStringFromObject(SignModelSingleton.INSTANCE.getInstance()))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void guest_sign_up_failure_test() throws Exception {
        when(accountService.executeSignUp(SignModelSingleton.INSTANCE.getInstance())).thenReturn(null);
        mockMvc
                .perform(post("/UserAPI/auth/guest/sign_up")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(this.jsonStringFromObject(SignModelSingleton.INSTANCE.getInstance()))
                )
                .andExpect(status().isNoContent());
    }

    @Test
    public void guest_id_confirm_success_test() throws Exception {
        when(userService.findByLoginId("USER_LOGIN_01")).thenReturn(UserVOUpdateSingleton.INSTANCE.getInstance());
        mockMvc.
                perform(get("/UserAPI/auth/guest/id_confirm/{loginId}", "USER_LOGIN_01"))
                .andExpect(status().isOk());
    }

    @Test
    public void guest_id_confirm_failure_test() throws Exception {
        when(userService.findByLoginId("USER_LOGIN_01")).thenReturn(null);
        mockMvc.
                perform(get("/UserAPI/auth/guest/id_confirm/{loginId}", "USER_LOGIN_ID01"))
                .andExpect(status().isOk());
    }

    @Test
    public void guest_age_list_test() throws Exception {
        when(ageService.findAll()).thenReturn(Arrays.asList(AgeVOSingleton.INSTANCE.getInstance()));
        mockMvc.
                perform(get("/UserAPI/auth/guest/ageList"))
                .andExpect(status().isOk());
    }

    @Test
    public void guest_city_list_test() throws Exception {
        when(cityService.findAll()).thenReturn(Arrays.asList(CityVOSingleton.INSTANCE.getInstance()));
        mockMvc.
                perform(get("/UserAPI/auth/guest/cityList"))
                .andExpect(status().isOk());
    }

    @Test
    public void guest_find_login_id_success_test() throws Exception {
        when(accountService.fetchLoginId(FindModelSingleton.INSTANCE.getInstance())).thenReturn("USER_LOGIN_ID01");
        mockMvc.
                perform(post("/UserAPI/auth/guest/find_loginId")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(this.jsonStringFromObject(FindModelSingleton.INSTANCE.getInstance())))
                .andExpect(status().isOk());
    }

    @Test
    public void guest_find_login_id_failure_test() throws Exception {
        when(accountService.fetchLoginId(FindModelSingleton.INSTANCE.getInstance())).thenReturn(null);
        mockMvc.
                perform(post("/UserAPI/auth/guest/find_loginId")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(this.jsonStringFromObject(FindModelSingleton.INSTANCE.getInstance())))
                .andExpect(status().isNoContent());
    }
}
