package io.kang.unit_test.testing_singleton.value_object_unit;

import io.kang.unit_test.testing_singleton.dto_unit.CommentDTOUpdateSingleton;
import io.kang.vo.MainCommentVO;

public enum MainCommentVOSingleton {
    INSTANCE;
    private MainCommentVO mainCommentVO = new MainCommentVO(CommentDTOUpdateSingleton.INSTANCE.getInstance().getId(), CommentDTOUpdateSingleton.INSTANCE.getInstance().getContext(), CommentDTOUpdateSingleton.INSTANCE.getInstance().getUserId(), CommentDTOUpdateSingleton.INSTANCE.getInstance().getWrittenDate(), 0L, 0L, false, false);
    public MainCommentVO getInstance(){
        if(this.mainCommentVO == null)
            return new MainCommentVO(CommentDTOUpdateSingleton.INSTANCE.getInstance().getId(), CommentDTOUpdateSingleton.INSTANCE.getInstance().getContext(), CommentDTOUpdateSingleton.INSTANCE.getInstance().getUserId(), CommentDTOUpdateSingleton.INSTANCE.getInstance().getWrittenDate(), 0L, 0L, false, false);
        else return this.mainCommentVO;
    }
}
