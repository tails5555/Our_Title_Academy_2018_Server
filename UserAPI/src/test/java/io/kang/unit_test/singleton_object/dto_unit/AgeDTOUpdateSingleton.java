package io.kang.unit_test.singleton_object.dto_unit;

import io.kang.dto.AgeDTO;

public enum AgeDTOUpdateSingleton {
    INSTANCE;
    private AgeDTO ageDTO = new AgeDTO(1L, "AGE_NAME01");
    public AgeDTO getInstance(){
        if(this.ageDTO == null)
            return new AgeDTO(1L, "AGE_NAME01");
        else return this.ageDTO;
    }
}
