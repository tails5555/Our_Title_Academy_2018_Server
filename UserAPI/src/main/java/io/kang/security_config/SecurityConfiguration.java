package io.kang.security_config;

import io.kang.component.JwtTokenProvider;
import io.kang.component.SecurityAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityAuthenticationProvider securityAuthenticationProvider;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private static final String[] SWAGGER_URI = {
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**"
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(SWAGGER_URI);
        web.ignoring().antMatchers(HttpMethod.GET, "/UserAPI/auth/resource/**");
        web.ignoring().antMatchers(HttpMethod.GET, "/UserAPI/auth/guest/**");
        web.ignoring().antMatchers(HttpMethod.POST, "/UserAPI/auth/guest/**");
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers(SWAGGER_URI).permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/UserAPI/auth/admin/**").hasRole("ADMIN")
                .antMatchers("/UserAPI/auth/manager/**").hasRole("MANAGER")
                .antMatchers("/UserAPI/auth/user/**").hasRole("USER")
                .antMatchers("/UserAPI/auth/common/**").hasAnyRole("ADMIN", "MANAGER", "USER");

        http.exceptionHandling().accessDeniedPage("/UserAPI/auth/common/denied");

        http.apply(new JwtTokenFilterConfig(this.jwtTokenProvider));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.securityAuthenticationProvider);
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
}
