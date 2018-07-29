package io.kang.service.integrate_service.interfaces;

import io.kang.dto.UserDTO;
import io.kang.exception.CustomException;

public interface ProviderLoginService {
    public UserDTO provideLogin(final String loginId, final String password) throws CustomException;
}
