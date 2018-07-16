package io.kang.unit_test.singleton_object.service_testing;

import io.kang.enumeration.Type;
import io.kang.vo.UserVO;

public enum UserVOUpdateSingleton {
    INSTANCE;
    private UserVO userVO = new UserVO(1L, "USER_LOGIN_ID01", "USER_NICKNAME01", "USER_PASSWORD01", Type.USER);
    public UserVO getInstance(){
        if(this.userVO == null)
            return new UserVO(1L, "USER_LOGIN_ID01", "USER_NICKNAME01", "USER_PASSWORD01", Type.USER);
        else return this.userVO;
    }
}
