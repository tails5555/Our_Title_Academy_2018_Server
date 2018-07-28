package io.kang.unit_test.testing_singleton.domain_unit;

import io.kang.domain.Request;

import java.time.LocalDateTime;

public enum RequestCreateSingleton {
    INSTANCE;
    private Request request = new Request(null, CategoryUpdateSingleton.INSTANCE.getInstance(), "REQUEST_USER_ID01", "REQUEST_INTRO_01", "REQUEST_CONTEXT_01", false, LocalDateTime.MIN, 0);
    public Request getInstance(){
        if(this.request == null)
            return new Request(null, CategoryUpdateSingleton.INSTANCE.getInstance(), "REQUEST_USER_ID01", "REQUEST_INTRO_01", "REQUEST_CONTEXT_01", false, LocalDateTime.MIN, 0);
        else return this.request;
    }
}
