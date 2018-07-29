package io.kang.component;

import io.kang.dto.UserDTO;
import io.kang.service.integrate_service.interfaces.ProviderLoginService;
import io.kang.util.UserType;
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
        UserDTO userDTO = providerLoginService.provideLogin(loginId, passwd);
        if (userDTO == null) return null;

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        String role = UserType.builtToUserType(userDTO.getUserType());
        grantedAuthorities.add(new SimpleGrantedAuthority(role));

        return new CheckedAuthentication(loginId, passwd, grantedAuthorities, userDTO);
    }

    @Override
    public boolean supports(Class<?> authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public class CheckedAuthentication extends UsernamePasswordAuthenticationToken {
        private static final long serialVersionUID = 1L;
        private UserDTO userDTO;

        public CheckedAuthentication(String loginId, String password, List<GrantedAuthority> grantedAuthorities, UserDTO userDTO){
            super(loginId, password, grantedAuthorities);
            this.userDTO = userDTO;
        }

        public UserDTO getUserDTO(){
            return this.userDTO;
        }

        public void setUserDTO(UserDTO userDTO){
            this.userDTO = userDTO;
        }
    }
}
