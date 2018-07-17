package io.kang.service.integrate_service.implement_object;

import io.kang.component.JwtTokenProvider;
import io.kang.exception.CustomException;
import io.kang.service.domain_service.interfaces.UserService;
import io.kang.service.integrate_service.interfaces.TokenLoginService;
import io.kang.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class TokenLoginServiceImpl implements TokenLoginService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String tokenLogin(final String loginId, final String password) throws CustomException {
        try {
            UserVO userVO = userService.findByLoginId(loginId);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginId, password));
            return jwtTokenProvider.createToken(loginId, userVO.getNickname(), userVO.getUserType());
        } catch (AuthenticationException e) {
            throw new CustomException(e.getMessage(), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }
}
