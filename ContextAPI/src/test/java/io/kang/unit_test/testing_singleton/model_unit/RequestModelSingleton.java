package io.kang.unit_test.testing_singleton.model_unit;

import io.kang.model.RequestModel;

public enum RequestModelSingleton {
    INSTANCE;
    private RequestModel requestModel = new RequestModel(null, "REQUEST_INTRO_01", "REQUEST_USER_ID01", "REQUEST_CONTEXT_01");
    public RequestModel getInstance(){
        if(this.requestModel == null)
            return new RequestModel(null, "REQUEST_INTRO_01", "REQUEST_USER_ID01", "REQUEST_CONTEXT_01");
        else return this.requestModel;
    }
}
