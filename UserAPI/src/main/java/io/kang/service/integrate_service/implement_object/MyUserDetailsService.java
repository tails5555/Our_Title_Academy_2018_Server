package io.kang.service.integrate_service.implement_object;

import io.kang.dto.UserDTO;
import io.kang.service.domain_service.interfaces.UserService;
import io.kang.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        final UserDTO userDTO = userService.findByLoginId(loginId);

        if (userDTO == null) {
            throw new UsernameNotFoundException("User '" + loginId + "' not found");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        String role = UserType.builtToUserType(userDTO.getUserType());
        grantedAuthorities.add(new SimpleGrantedAuthority(role));

        return org.springframework.security.core.userdetails.User
                .withUsername(loginId)
                .password(userDTO.getPassword())
                .authorities(grantedAuthorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}