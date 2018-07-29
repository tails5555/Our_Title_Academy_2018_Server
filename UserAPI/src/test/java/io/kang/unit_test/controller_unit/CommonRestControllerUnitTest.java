package io.kang.unit_test.controller_unit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import io.kang.enumeration.Type;
import io.kang.rest_controller.CommonRestController;
import io.kang.service.domain_service.implement_object.AgeServiceImpl;
import io.kang.service.domain_service.implement_object.CityServiceImpl;
import io.kang.service.domain_service.interfaces.AgeService;
import io.kang.service.domain_service.interfaces.CityService;
import io.kang.service.integrate_service.implement_object.AccountServiceImpl;
import io.kang.service.integrate_service.interfaces.AccountService;
import io.kang.unit_test.singleton_object.dto_unit.AgeDTOUpdateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.CityDTOUpdateSingleton;
import io.kang.unit_test.singleton_object.dto_unit.DetailDTOUpdateSingleton;
import io.kang.unit_test.singleton_object.model_unit.SignModelSingleton;
import io.kang.vo.AccessVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.Filter;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {io.kang.security_config.SecurityConfiguration.class})
@WebAppConfiguration
@SpringBootTest(classes = {io.kang.UserApiApplication.class})
public class CommonRestControllerUnitTest {
    private MockMvc mockMvc;

    @Value("${tmpToken.admin_token}")
    private String adminToken;

    @Mock
    private AccountService accountService = new AccountServiceImpl();

    @Mock
    private AgeService ageService = new AgeServiceImpl();

    @Mock
    private CityService cityService = new CityServiceImpl();

    @InjectMocks
    private CommonRestController commonRestController;

    @Autowired
    private Filter springSecurityFilterChain;

    private Principal builtMockPrincipal(String loginId){
        return new Principal() {
            @Override
            public String getName() {
                return loginId;
            }
        };
    }

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
                .standaloneSetup(commonRestController)
                .addFilters()
                .alwaysDo(print())
                .apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain))
                .build();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser(username = "kang123", password = "test123", authorities = {"ROLE_ADMIN"})
    public void any_user_denied_test() throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", adminToken));

        mockMvc
                .perform(
                        get("/UserAPI/auth/common/denied")
                            .headers(headers)
                )
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "kang123", password = "test123", authorities = {"ROLE_ADMIN"})
    public void any_user_fetch_current_access_test() throws Exception{
        Principal principal = this.builtMockPrincipal("kang123");
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/UserAPI/auth/common/current_access");

        when(accountService.fetchCurrentAccess(principal, request)).thenReturn(new AccessVO("kang123", "강관리자", Type.ADMIN, LocalDateTime.now()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", adminToken));

        mockMvc
                .perform(
                        get("/UserAPI/auth/common/current_access")
                            .headers(headers)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "kang123", password = "test123", authorities = {"ROLE_ADMIN"})
    public void any_user_fetch_sign_info_test() throws Exception{
        Principal principal = this.builtMockPrincipal("kang123");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", adminToken));

        when(accountService.fetchSignInfo(principal)).thenReturn(null);

        mockMvc
                .perform(
                        get("/UserAPI/auth/common/sign_info")
                            .headers(headers)
                )
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "kang123", password = "test123", authorities = {"ROLE_ADMIN"})
    public void any_user_sign_update_test() throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", adminToken));

        when(accountService.executeSignInfoUpdate(SignModelSingleton.INSTANCE.getInstance())).thenReturn(DetailDTOUpdateSingleton.INSTANCE.getInstance());

        mockMvc
                .perform(
                        put("/UserAPI/auth/common/sign_update")
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .content(jsonStringFromObject(SignModelSingleton.INSTANCE.getInstance()))
                            .headers(headers)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username="kang123", password = "test123", authorities = {"ROLE_ADMIN"})
    public void any_user_logout_test() throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", adminToken));

        mockMvc
                .perform(
                        delete("/UserAPI/auth/common/logout")
                                .headers(headers)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username="kang123", password="test123", authorities = {"ROLE_ADMIN"})
    public void any_user_volunteer_fired_test() throws Exception{
        doNothing().when(accountService).executeVolunteerFired(this.builtMockPrincipal("kang123"));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", adminToken));

        mockMvc
                .perform(
                        delete("/UserAPI/auth/common/volunteer_fired")
                                .headers(headers)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username="kang123", password="test123", authorities = {"ROLE_ADMIN"})
    public void any_user_age_list_test() throws Exception{
        when(ageService.findAll()).thenReturn(Arrays.asList(AgeDTOUpdateSingleton.INSTANCE.getInstance()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", adminToken));

        mockMvc
                .perform(
                        get("/UserAPI/auth/common/ageList")
                                .headers(headers)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username="kang123", password="test123", authorities = {"ROLE_ADMIN"})
    public void any_user_city_list_test() throws Exception{
        when(cityService.findAll()).thenReturn(Arrays.asList(CityDTOUpdateSingleton.INSTANCE.getInstance()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", adminToken));

        mockMvc
                .perform(
                        get("/UserAPI/auth/common/cityList")
                                .headers(headers)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username="kang123", password="test123", authorities = {"ROLE_ADMIN"})
    public void any_user_confirm_password_test() throws Exception{
        when(accountService.confirmCurrentPassword(this.builtMockPrincipal("kang123"), "password")).thenReturn(true);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", adminToken));

        mockMvc
                .perform(
                        post("/UserAPI/auth/common/confirm_password")
                                .headers(headers)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content("kang123")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
