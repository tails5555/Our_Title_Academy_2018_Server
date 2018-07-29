package io.kang.unit_test.singleton_object.domain_unit;

import io.kang.domain.Age;

public enum AgeCreateSingleton {
    INSTANCE;
    private Age age = new Age(null, "AGE_NAME01");
    public Age getInstance(){
        if(this.age == null)
            return new Age(null, "AGE_NAME01");
        else return this.age;
    }
}
