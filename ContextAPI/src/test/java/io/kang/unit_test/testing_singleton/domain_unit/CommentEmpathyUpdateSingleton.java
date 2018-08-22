package io.kang.unit_test.testing_singleton.domain_unit;

import io.kang.domain.mysql.CommentEmpathy;
import io.kang.enumeration.Status;

import java.time.LocalDateTime;

public enum CommentEmpathyUpdateSingleton {
    INSTANCE;
    private CommentEmpathy commentEmpathy = new CommentEmpathy(1L, Status.LIKE, LocalDateTime.MIN, "EMPATHY_USER_ID01", CommentUpdateSingleton.INSTANCE.getInstance());
    public CommentEmpathy getInstance(){
        if(this.commentEmpathy == null)
            return new CommentEmpathy(1L, Status.LIKE, LocalDateTime.MIN, "EMPATHY_USER_ID01", CommentUpdateSingleton.INSTANCE.getInstance());
        else return this.commentEmpathy;
    }
}
