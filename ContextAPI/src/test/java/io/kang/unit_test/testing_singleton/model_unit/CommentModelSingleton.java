package io.kang.unit_test.testing_singleton.model_unit;

import io.kang.model.CommentModel;

public enum CommentModelSingleton {
    INSTANCE;
    private CommentModel commentModel = new CommentModel(1L, "COMMENT_USER_ID01", 1L, "COMMENT_CONTEXT01");
    public CommentModel getInstance(){
        if(this.commentModel == null)
            return new CommentModel(1L, "COMMENT_USER_ID01", 1L, "COMMENT_CONTEXT01");
        else return this.commentModel;
    }
}
