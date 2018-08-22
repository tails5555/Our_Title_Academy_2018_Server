package io.kang.unit_test.testing_singleton.domain_unit;

import io.kang.domain.mysql.RequestEmpathy;
import io.kang.domain.mysql.TitleEmpathy;
import io.kang.enumeration.Status;

import java.time.LocalDateTime;

public enum TitleEmpathyCreateSingleton {
    INSTANCE;
    private TitleEmpathy titleEmpathy = new TitleEmpathy(null, Status.LIKE, LocalDateTime.MIN, "EMPATHY_USER_ID01", TitleUpdateSingleton.INSTANCE.getInstance());
    public TitleEmpathy getInstance(){
        if(this.titleEmpathy == null)
            return new TitleEmpathy(null, Status.LIKE, LocalDateTime.MIN, "EMPATHY_USER_ID01", TitleUpdateSingleton.INSTANCE.getInstance());
        else return this.titleEmpathy;
    }
}
