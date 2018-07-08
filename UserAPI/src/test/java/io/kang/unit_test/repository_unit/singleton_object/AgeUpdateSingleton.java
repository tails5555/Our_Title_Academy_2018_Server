package io.kang.unit_test.repository_unit.singleton_object;

import io.kang.domain.Age;

public enum AgeUpdateSingleton {
    INSTANCE;
    private Age age = new Age(1L, "AGE_NAME01");
    public Age getInstance(){
        if(this.age == null)
            return new Age(1L, "AGE_NAME01");
        else return this.age;
    }
}
