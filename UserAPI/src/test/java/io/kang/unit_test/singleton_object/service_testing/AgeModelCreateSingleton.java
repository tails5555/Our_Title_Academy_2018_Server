package io.kang.unit_test.singleton_object.service_testing;

import io.kang.model.AgeModel;

public enum AgeModelCreateSingleton {
    INSTANCE;
    private AgeModel ageModel = new AgeModel(null, "AGE_NAME01");
    public AgeModel getInstance(){
        if(this.ageModel == null)
            return new AgeModel(null, "AGE_NAME01");
        return this.ageModel;
    }
}
