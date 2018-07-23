package io.kang.security_config;

import io.kang.component.JwtTokenProvider;
import io.kang.exception.CustomException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {
    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, CustomException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        boolean validated = false;
        try {
            validated = jwtTokenProvider.validateToken(token);
        } catch(CustomException e){
            throw new CustomException(e.getMessage(), e.getHttpStatus());
        } finally {
            if (token != null && validated) {
                Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(req, res);
        }
    }
}