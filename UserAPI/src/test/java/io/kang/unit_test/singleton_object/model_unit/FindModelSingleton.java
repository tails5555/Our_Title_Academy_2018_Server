package io.kang.unit_test.singleton_object.model_unit;

import io.kang.model.FindModel;

public enum FindModelSingleton {
    INSTANCE;
    private FindModel findModel = new FindModel("USER_LOGIN_ID01", "NAME1");
    public FindModel getInstance(){
        if(this.findModel == null)
            return new FindModel("USER_LOGIN_ID01", "NAME1");
        else return this.findModel;
    }
}
