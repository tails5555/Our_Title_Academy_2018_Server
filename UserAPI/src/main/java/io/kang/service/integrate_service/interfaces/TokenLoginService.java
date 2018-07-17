package io.kang.service.integrate_service.interfaces;

import io.kang.exception.CustomException;

public interface TokenLoginService {
    public String tokenLogin(final String loginId, final String password) throws CustomException;
}
