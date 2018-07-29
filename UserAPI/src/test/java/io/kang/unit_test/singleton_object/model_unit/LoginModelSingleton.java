package io.kang.unit_test.singleton_object.model_unit;

import io.kang.model.LoginModel;

public enum LoginModelSingleton {
    INSTANCE;
    private LoginModel loginModel = new LoginModel("LOGIN_ID01", "LOGIN_PASSWORD01");
    public LoginModel getInstance(){
        if(this.loginModel == null)
            return new LoginModel("LOGIN_ID01", "LOGIN_PASSWORD01");
        else return this.loginModel;
    }
}
