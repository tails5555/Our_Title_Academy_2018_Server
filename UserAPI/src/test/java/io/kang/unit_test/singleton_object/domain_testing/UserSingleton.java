package io.kang.unit_test.singleton_object.domain_testing;

import io.kang.domain.User;
import io.kang.enumeration.Type;

public enum UserSingleton {
    INSTANCE;
    private User user = new User(1L, "USER_NAME01", "USER_NICKNAME01", "USER_PASSWORD01", Type.USER);
    public User getInstance(){
        if(user==null){
            return new User(1L, "USER_NAME01", "USER_NICKNAME01", "USER_PASSWORD01", Type.USER);
        }
        else return user;
    }
}
