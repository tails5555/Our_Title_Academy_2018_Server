package io.kang.unit_test.controller_unit;

import io.kang.enumeration.Type;
import io.kang.rest_controller.AdminRestController;
import io.kang.service.integrate_service.implement_object.AccountServiceImpl;
import io.kang.service.integrate_service.interfaces.AccountService;
import io.kang.unit_test.singleton_object.service_testing.DetailVOUpdateSingleton;
import io.kang.unit_test.singleton_object.service_testing.UserVOUpdateSingleton;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {io.kang.security_config.SecurityConfiguration.class})
@WebAppConfiguration
@SpringBootTest(classes = {io.kang.UserApiApplication.class})
public class AdminRestControllerUnitTest {
    private MockMvc mockMvc;

    @Value("${tmpToken.admin_token}")
    private String adminToken;

    @Mock
    private AccountService accountService = new AccountServiceImpl();

    @InjectMocks
    private AdminRestController adminRestController;

    @Autowired
    private Filter springSecurityFilterChain;

    @Before
    public void set_up() throws Exception{
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(adminRestController)
                .addFilters()
                .alwaysDo(print())
                .apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain))
                .build();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser(username = "kang123", password = "test123", authorities = {"ROLE_ADMIN"})
    public void admin_fetch_user_list_test() throws Exception {
        when(accountService.fetchUserList()).thenReturn(Arrays.asList(new PrincipalVO("LOGIN_ID", "NAME", "NICKNAME", Type.USER)));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", adminToken));

        mockMvc
                .perform(
                        get("/UserAPI/auth/admin/user_list")
                                .headers(headers)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "kang123", password = "test123", authorities = {"ROLE_ADMIN"})
    public void admin_fetch_any_user_info_test() throws Exception {
        when(accountService.fetchDetailInfo("USER_LOGIN_ID01")).thenReturn(DetailVOUpdateSingleton.INSTANCE.getInstance());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", adminToken));

        mockMvc
                .perform(
                        get("/UserAPI/auth/admin/user_info/{loginId}", "USER_LOGIN_ID01")
                                .headers(headers)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "kang123", password = "test123", authorities = {"ROLE_ADMIN"})
    public void admin_execute_type_change_test() throws Exception {
        when(accountService.executeAdminLevelChange("USER_LOGIN_ID01", Type.USER)).thenReturn(UserVOUpdateSingleton.INSTANCE.getInstance());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", adminToken));

        mockMvc
                .perform(
                        put("/UserAPI/auth/admin/type_change/{loginId}/{typeSequence}", "USER_LOGIN_ID01", 3)
                                .headers(headers)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "kang123", password = "test123", authorities = {"ROLE_ADMIN"})
    public void admin_force_fired_test() throws Exception{
        doNothing().when(accountService).executeForceFired("USER_LOGIN_ID01");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", adminToken));

        mockMvc
                .perform(
                        delete("/UserAPI/auth/admin/force_fired/{loginId}", "USER_LOGIN_ID01")
                                .headers(headers)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
