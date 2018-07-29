package io.kang.unit_test.singleton_object.domain_unit;

import io.kang.domain.User;
import io.kang.enumeration.Type;

public enum UserCreateSingleton {
    INSTANCE;
    private User user = new User(null, "USER_LOGIN_ID01", "USER_NICKNAME01", "USER_PASSWORD01", Type.USER);
    public User getInstance(){
        if(this.user==null){
            return new User(null, "USER_LOGIN_ID01", "USER_NICKNAME01", "USER_PASSWORD01", Type.USER);
        }
        else return this.user;
    }
}
