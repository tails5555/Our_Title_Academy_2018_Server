package io.kang.unit_test.testing_singleton.model_unit;

import io.kang.model.TitleModel;

public enum TitleModelSingleton {
    INSTANCE;
    private TitleModel titleModel = new TitleModel("TITLE_USER_ID01", 1L, "TITLE_CONTEXT_01");
    public TitleModel getInstance(){
        if(this.titleModel == null)
            return new TitleModel("TITLE_USER_ID01", 1L, "TITLE_CONTEXT_01");
        else return this.titleModel;
    }
}
