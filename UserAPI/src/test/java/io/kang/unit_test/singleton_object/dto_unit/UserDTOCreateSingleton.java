package io.kang.unit_test.singleton_object.dto_unit;

import io.kang.dto.UserDTO;
import io.kang.enumeration.Type;

public enum UserDTOCreateSingleton {
    INSTANCE;
    private UserDTO userDTO = new UserDTO(null, "USER_LOGIN_ID01", "USER_NICKNAME01", "USER_PASSWORD01", Type.USER);
    public UserDTO getInstance(){
        if(this.userDTO == null)
            return new UserDTO(null, "USER_LOGIN_ID01", "USER_NICKNAME01", "USER_PASSWORD01", Type.USER);
        else return this.userDTO;
    }
}
