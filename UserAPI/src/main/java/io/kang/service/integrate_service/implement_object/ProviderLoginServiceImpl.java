package io.kang.service.integrate_service.implement_object;

import io.kang.dto.UserDTO;
import io.kang.exception.CustomException;
import io.kang.service.domain_service.interfaces.UserService;
import io.kang.service.integrate_service.interfaces.ProviderLoginService;
import io.kang.util.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProviderLoginServiceImpl implements ProviderLoginService {
    @Autowired
    private UserService userService;

    @Override
    public UserDTO provideLogin(final String loginId, final String password) throws CustomException {
        if(loginId == null || loginId.isEmpty())
            throw new CustomException("ID의 값을 입력하지 않았습니다.", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        UserDTO userDTO = userService.findByLoginId(loginId);
        if(userDTO == null)
            throw new CustomException("존재하지 않는 사용자의 ID를 입력하였습니다.", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        else {
            String encryptPassword = Encryption.encrypt(password, Encryption.MD5);
            if(!userDTO.getPassword().equals(encryptPassword))
                throw new CustomException("사용자의 비밀번호가 일치하지 않습니다.", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            else
                return userDTO;
        }
    }
}
