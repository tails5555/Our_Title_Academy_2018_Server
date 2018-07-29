package io.kang.unit_test.testing_singleton.domain_unit;

import io.kang.domain.mysql.Title;

import java.time.LocalDateTime;

public enum TitleCreateSingleton {
    INSTANCE;
    private Title title = new Title(null, RequestUpdateSingleton.INSTANCE.getInstance(), "TITLE_USER_ID01", "TITLE_CONTEXT_01", LocalDateTime.MIN);
    public Title getInstance(){
        if(this.title == null)
            return new Title(null, RequestUpdateSingleton.INSTANCE.getInstance(), "TITLE_USER_ID01", "TITLE_CONTEXT_01", LocalDateTime.MIN);
        else return this.title;
    }
}
