package io.kang.unit_test.testing_singleton.domain_unit;

import io.kang.domain.mysql.Comment;

import java.time.LocalDateTime;

public enum CommentUpdateSingleton {
    INSTANCE;
    private Comment comment = new Comment(1L, "COMMENT_USER_ID01", RequestUpdateSingleton.INSTANCE.getInstance(), "COMMENT_CONTEXT01", LocalDateTime.MIN);
    public Comment getInstance(){
        if(this.comment == null)
            return new Comment(1L, "COMMENT_USER_ID01", RequestUpdateSingleton.INSTANCE.getInstance(), "COMMENT_CONTEXT01", LocalDateTime.MIN);
        else return this.comment;
    }
}
