package io.kang.unit_test.singleton_object.value_object_testing;

import io.kang.vo.AgeVO;

public enum AgeVOSingleton {
    INSTANCE;
    private AgeVO ageVO = new AgeVO(1L, "AGE_NAME01");
    public AgeVO getInstance(){
        if(this.ageVO == null)
            return new AgeVO(1L, "AGE_NAME01");
        else return this.ageVO;
    }
}
