package io.kang.service.integrate_service.interfaces;

import io.kang.vo.UserVO;

public interface ProviderLoginService {
    public UserVO provideLogin(final String loginId, final String password);
}