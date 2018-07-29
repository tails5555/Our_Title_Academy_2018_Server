package io.kang.unit_test.singleton_object.vo_unit;

import io.kang.enumeration.Type;
import io.kang.vo.AccessVO;

import java.time.LocalDateTime;

public enum AccessVOSingleton {
    INSTANCE;
    private AccessVO accessVO = new AccessVO("USER_LOGIN_ID01", "USER_NICKNAME01", Type.USER, LocalDateTime.MIN);
    public AccessVO getInstance(){
        if(this.accessVO == null)
            return new AccessVO("USER_LOGIN_ID01", "USER_NICKNAME01", Type.USER, LocalDateTime.MIN);
        else return this.accessVO;
    }
}
