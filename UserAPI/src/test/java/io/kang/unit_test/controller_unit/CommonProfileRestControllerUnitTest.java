package io.kang.unit_test.controller_unit;

import io.kang.enumeration.Type;
import io.kang.rest_controller.CommonProfileRestController;
import io.kang.service.integrate_service.implement_object.ProfileFetchServiceImpl;
import io.kang.service.integrate_service.interfaces.ProfileFetchService;
import io.kang.unit_test.singleton_object.service_testing.ProfileVOUpdateSingleton;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.Filter;
import java.security.Principal;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {io.kang.security_config.SecurityConfiguration.class})
@WebAppConfiguration
@SpringBootTest(classes = {io.kang.UserApiApplication.class})
public class CommonProfileRestControllerUnitTest {
    private MockMvc mockMvc;

    @Value("${tmpToken.admin_token}")
    private String adminToken;

    @Mock
    private ProfileFetchService profileFetchService = new ProfileFetchServiceImpl();

    @InjectMocks
    private CommonProfileRestController commonProfileRestController;

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

    @Before
    public void set_up() throws Exception{
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(commonProfileRestController)
                .addFilters()
                .alwaysDo(print())
                .apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain))
                .build();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser(username = "kang123", password = "test123", authorities = {"ROLE_ADMIN"})
    public void any_profile_my_fetch_list_test() throws Exception {
        when(profileFetchService.fetchByCurrentPrincipal(this.builtMockPrincipal("kang123"))).thenReturn(null);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", adminToken));

        mockMvc
                .perform(
                        get("/UserAPI/auth/common/profile/my_fetch")
                                .headers(headers)
                )
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "kang123", password = "test123", authorities = {"ROLE_ADMIN"})
    public void any_profile_upload_test() throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile("data", "image.jpg", "image/jpeg", new byte[0]);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", adminToken));

        doNothing().when(profileFetchService).profileUpload(mockFile, this.builtMockPrincipal("kang123"));

        mockMvc
                .perform(
                        multipart("/UserAPI/auth/common/profile/upload")
                            .file(mockFile)
                            .headers(headers)
                )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "kang123", password = "test123", authorities = {"ROLE_ADMIN"})
    public void any_profile_release_test() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", adminToken));

        doNothing().when(profileFetchService).executeProfileRemove(this.builtMockPrincipal("kang123"));

        mockMvc
                .perform(
                        delete("/UserAPI/auth/common/profile/release")
                                .headers(headers)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void resource_profile_another_fetch() throws Exception {
        when(profileFetchService.fetchByUserLoginId("USER_LOGIN_ID01")).thenReturn(ProfileVOUpdateSingleton.INSTANCE.getInstance());

        mockMvc
                .perform(
                        get("/UserAPI/auth/resource/profile/another_fetch/{loginId}", "USER_LOGIN_ID01")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void resource_image_profile_view() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", String.format("Bearer %s", adminToken));

        when(profileFetchService.fetchByUserLoginId("USER_LOGIN_ID01")).thenReturn(ProfileVOUpdateSingleton.INSTANCE.getInstance());

        mockMvc
                .perform(
                        get("/UserAPI/auth/resource/profile/image_profile/{loginId}", "USER_LOGIN_ID01")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
