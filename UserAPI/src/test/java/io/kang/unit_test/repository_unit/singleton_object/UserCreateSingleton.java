package io.kang.unit_test.repository_unit.singleton_object;

import io.kang.domain.User;
import io.kang.enumeration.Type;

public enum UserCreateSingleton {
    INSTANCE;
    private User user = new User(null, "USER_LOGIN_ID00", "USER_NICKNAME00", "USER_PASSWORD00", Type.USER);
    public User getInstance(){
        if(this.user==null){
            return new User(null, "USER_LOGIN_ID00", "USER_NICKNAME00", "USER_PASSWORD00", Type.USER);
        }
        else return this.user;
    }
}
