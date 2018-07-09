package io.kang.unit_test.singleton_object.domain_testing;

import io.kang.domain.Age;

public enum AgeSingleton {
    INSTANCE;
    private Age age = new Age(1L, "AGE01");
    public Age getInstance(){
        if(age==null)
            return new Age(1L, "AGE01");
        else return age;
    }
}
