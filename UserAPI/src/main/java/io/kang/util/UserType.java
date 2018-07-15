package io.kang.util;

import io.kang.enumeration.Type;

public class UserType {
    public static String builtToUserType(Type type){
        switch(type){
            case ADMIN :
                return "ROLE_ADMIN";
            case MANAGER :
                return "ROLE_MANAGER";
            case USER :
                return "ROLE_USER";
        }
        return null;
    }
}
