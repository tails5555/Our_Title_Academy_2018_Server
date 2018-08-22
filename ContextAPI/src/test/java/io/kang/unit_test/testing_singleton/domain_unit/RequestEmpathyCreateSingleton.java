package io.kang.unit_test.testing_singleton.domain_unit;

import io.kang.domain.mysql.RequestEmpathy;
import io.kang.enumeration.Status;

import java.time.LocalDateTime;

public enum RequestEmpathyCreateSingleton {
    INSTANCE;
    private RequestEmpathy requestEmpathy = new RequestEmpathy(null, Status.LIKE, LocalDateTime.MIN, "EMPATHY_USER_ID01", RequestUpdateSingleton.INSTANCE.getInstance());
    public RequestEmpathy getInstance(){
        if(this.requestEmpathy == null)
            return new RequestEmpathy(null, Status.LIKE, LocalDateTime.MIN, "EMPATHY_USER_ID01", RequestUpdateSingleton.INSTANCE.getInstance());
        else return this.requestEmpathy;
    }
}
