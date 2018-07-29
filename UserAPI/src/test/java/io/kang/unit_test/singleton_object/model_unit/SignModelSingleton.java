package io.kang.unit_test.singleton_object.model_unit;

import io.kang.model.SignModel;

public enum SignModelSingleton {
    INSTANCE;
    private SignModel signModel = new SignModel("USER_LOGIN_ID01", "USER_PASSWORD01", "USER_PASSWORD01", "NAME1",  "NICKNAME01", "DETAIL_EMAIL01", "HOME_NUMBER01", "PHONE_NUMBER01", 1L, 1L);
    public SignModel getInstance(){
        if(this.signModel == null)
            return new SignModel("USER_LOGIN_ID01", "USER_PASSWORD01", "USER_PASSWORD01", "NAME1",  "NICKNAME01", "DETAIL_EMAIL01", "HOME_NUMBER01", "PHONE_NUMBER01", 1L, 1L);
        else return this.signModel;
    }
}
