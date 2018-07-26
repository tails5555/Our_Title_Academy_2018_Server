package io.kang.unit_test.controller_unit;

import ch.qos.logback.core.encoder.EchoEncoder;
import io.kang.enumeration.Type;
import io.kang.rest_controller.ManagerRestController;
import io.kang.service.integrate_service.implement_object.AccountServiceImpl;
import io.kang.service.integrate_service.interfaces.AccountService;
import io.kang.unit_test.singleton_object.service_testing.DetailVOUpdateSingleton;
import io.kang.vo.PrincipalVO;
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
import org.springframework.security.test.context.support.WithMockUser;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {io.kang.security_config.SecurityConfiguration.class})
@WebAppConfiguration
@SpringBootTest(classes = {io.kang.UserApiApplication.class})
public class ManagerRestControllerUnitTest {
    private MockMvc mockMvc;

    @Value("${tmpToken.manager_token}")
    private String managerToken;

    @Mock
    private AccountService accountService = new AccountServiceImpl();

    @InjectMocks
    private ManagerRestController managerRestController;

    @Autowired
    private Filter springSecurityFilterChain;

    @Before
    public void set_up() throws Exception{
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(managerRestController)
                .addFilters()
                .alwaysDo(print())
                .apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain))
                .build();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser(username = "kang123", password = "test123", authorities = {"ROLE_MANAGER"})
    public void manager_fetch_user_list_test() throws Exception {
        when(accountService.fetchUserList()).thenReturn(Arrays.asList(new PrincipalVO("LOGIN_ID", "NAME", "NICKNAME", Type.USER)));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", managerToken));

        mockMvc
                .perform(
                        get("/UserAPI/auth/manager/user_list")
                                .headers(headers)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "kang123", password = "test123", authorities = {"ROLE_MANAGER"})
    public void manager_fetch_any_user_info_test() throws Exception {
        when(accountService.fetchDetailInfo("USER_LOGIN_ID01")).thenReturn(DetailVOUpdateSingleton.INSTANCE.getInstance());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", managerToken));

        mockMvc
                .perform(
                        get("/UserAPI/auth/manager/user_info/{loginId}", "USER_LOGIN_ID01")
                                .headers(headers)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "kang123", password = "test123", authorities = {"ROLE_MANAGER"})
    public void manager_level_up_test() throws Exception {
        when(accountService.fetchDetailInfo("USER_LOGIN_ID01")).thenReturn(DetailVOUpdateSingleton.INSTANCE.getInstance());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", managerToken));

        mockMvc
                .perform(
                        put("/UserAPI/auth/manager/manager_up/{loginId}", "USER_LOGIN_ID01")
                                .headers(headers)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
