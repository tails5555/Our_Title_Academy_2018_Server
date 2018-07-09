package io.kang.unit_test.singleton_object.repository_testing;

import io.kang.domain.User;
import io.kang.enumeration.Type;

public enum UserUpdateSingleton {
    INSTANCE;
    private User user = new User(1L, "USER_LOGIN_ID01", "USER_NICKNAME01", "USER_PASSWORD01", Type.USER);
    public User getInstance(){
        if(this.user==null){
            return new User(1L, "USER_LOGIN_ID01", "USER_NICKNAME01", "USER_PASSWORD01", Type.USER);
        }
        else return this.user;
    }
}
