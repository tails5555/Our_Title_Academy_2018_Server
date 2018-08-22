package io.kang.unit_test.testing_singleton.dto_unit;

import io.kang.dto.mysql.CommentEmpathyDTO;
import io.kang.enumeration.Status;

import java.time.LocalDateTime;

public enum CommentEmpathyDTOUpdateSingleton {
    INSTANCE;
    private CommentEmpathyDTO commentEmpathyDTO = new CommentEmpathyDTO(1L, Status.LIKE, LocalDateTime.MIN, "EMPATHY_USER_ID01", CommentDTOUpdateSingleton.INSTANCE.getInstance());
    public CommentEmpathyDTO getInstance(){
        if(this.commentEmpathyDTO == null)
            return new CommentEmpathyDTO(1L, Status.LIKE, LocalDateTime.MIN, "EMPATHY_USER_ID01", CommentDTOUpdateSingleton.INSTANCE.getInstance());
        else return this.commentEmpathyDTO;
    }
}
