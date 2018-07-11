package io.kang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginModel {
    private String loginId;
    private String password;
    public static LoginModel builtToModel(String loginId, String password){
        return new LoginModel(loginId, password);
    }
}
