package io.kang.unit_test.testing_singleton.dto_unit;

import io.kang.dto.mysql.CommentDTO;

import java.time.LocalDateTime;

public enum CommentDTOUpdateSingleton {
    INSTANCE;
    private CommentDTO commentDTO = new CommentDTO(1L, "COMMENT_USER_ID01", RequestDTOUpdateSingleton.INSTANCE.getInstance(), "COMMENT_CONTEXT01", LocalDateTime.MIN);
    public CommentDTO getInstance(){
        if(this.commentDTO == null)
            return new CommentDTO(1L, "COMMENT_USER_ID01", RequestDTOUpdateSingleton.INSTANCE.getInstance(), "COMMENT_CONTEXT01", LocalDateTime.MIN);
        else return this.commentDTO;
    }
}
