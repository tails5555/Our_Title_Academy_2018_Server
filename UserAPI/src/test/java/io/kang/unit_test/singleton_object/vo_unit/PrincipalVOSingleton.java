package io.kang.unit_test.singleton_object.vo_unit;

import io.kang.enumeration.Type;
import io.kang.vo.PrincipalVO;

public enum PrincipalVOSingleton {
    INSTANCE;
    private PrincipalVO principalVO = new PrincipalVO("USER_LOGIN_ID01", "NAME1", "USER_NICKNAME01", Type.USER);
    public PrincipalVO getInstance(){
        if(this.principalVO == null)
            return new PrincipalVO("USER_LOGIN_ID01", "NAME1", "USER_NICKNAME01", Type.USER);
        else return this.principalVO;
    }
}
