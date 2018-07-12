package io.kang.unit_test.singleton_object.service_testing;

import io.kang.model.AgeModel;

public enum AgeModelUpdateSingleton {
    INSTANCE;
    private AgeModel ageModel = new AgeModel(1L, "AGE_NAME01");
    public AgeModel getInstance(){
        if(this.ageModel == null)
            return new AgeModel(1L, "AGE_NAME01");
        else return this.ageModel;
    }
}
