package io.kang.service.integrate_service.implement_object;

import io.kang.repository.UserRepository;
import io.kang.service.domain_service.interfaces.UserService;
import io.kang.util.UserType;
import io.kang.vo.UserVO;
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
        final UserVO userVO = userService.findByLoginId(loginId);

        if (userVO == null) {
            throw new UsernameNotFoundException("User '" + loginId + "' not found");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        String role = UserType.builtToUserType(userVO.getUserType());
        grantedAuthorities.add(new SimpleGrantedAuthority(role));

        return org.springframework.security.core.userdetails.User
                .withUsername(loginId)
                .password(userVO.getPassword())
                .authorities(grantedAuthorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}