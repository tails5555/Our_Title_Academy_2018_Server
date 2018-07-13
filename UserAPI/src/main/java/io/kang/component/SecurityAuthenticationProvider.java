package io.kang.component;

import io.kang.service.integrate_service.interfaces.ProviderLoginService;
import io.kang.util.UserType;
import io.kang.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private ProviderLoginService providerLoginService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginId = authentication.getName();
        String passwd = authentication.getCredentials().toString();
        return this.authenticate(loginId, passwd);
    }

    private Authentication authenticate(String loginId, String passwd) throws AuthenticationException {
        UserVO userVO = providerLoginService.provideLogin(loginId, passwd);
        if (userVO == null) return null;

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        String role = UserType.builtToUserType(userVO.getUserType());
        grantedAuthorities.add(new SimpleGrantedAuthority(role));

        return new CheckedAuthentication(loginId, passwd, grantedAuthorities, userVO);
    }

    @Override
    public boolean supports(Class<?> authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public class CheckedAuthentication extends UsernamePasswordAuthenticationToken {
        private static final long serialVersionUID = 1L;
        private UserVO userVO;

        public CheckedAuthentication(String loginId, String password, List<GrantedAuthority> grantedAuthorities, UserVO userVO){
            super(loginId, password, grantedAuthorities);
            this.userVO = userVO;
        }

        public UserVO getUserVO(){
            return this.userVO;
        }

        public void setUserVO(UserVO userVO){
            this.userVO = userVO;
        }
    }
}
