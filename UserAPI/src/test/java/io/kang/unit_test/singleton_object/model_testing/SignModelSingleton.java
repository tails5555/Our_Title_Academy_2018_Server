package io.kang.unit_test.singleton_object.model_testing;

import io.kang.model.SignModel;

public enum SignModelSingleton {
    INSTANCE;
    private SignModel signModel = new SignModel("LOGIN_ID01", "SIGN_MAIN_PASSWORD01", "SIGN_SUB_PASSWORD01", "NAME01", "SIGN_EMAIL01", "HOME_NUMBER01", "PHONE_NUMBER01", 1L, 1L);
    public SignModel getInstance(){
        if(this.signModel == null)
            return new SignModel("LOGIN_ID01", "SIGN_MAIN_PASSWORD01", "SIGN_SUB_PASSWORD01", "NAME01", "SIGN_EMAIL01", "HOME_NUMBER01", "PHONE_NUMBER01", 1L, 1L);
        else return this.signModel;
    }
}
