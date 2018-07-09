package io.kang.unit_test.singleton_object.repository_testing;

import io.kang.domain.Age;

public enum AgeCreateSingleton {
    INSTANCE;
    private Age age = new Age(null, "AGE_NAME00");
    public Age getInstance(){
        if(this.age == null)
            return new Age(null, "AGE_NAME00");
        else return this.age;
    }
}
