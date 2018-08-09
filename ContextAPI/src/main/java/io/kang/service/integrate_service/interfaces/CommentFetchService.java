package io.kang.service.integrate_service.interfaces;

import io.kang.model.CommentModel;
import io.kang.vo.MainCommentVO;

import java.util.List;

public interface CommentFetchService {
    public List<MainCommentVO> fetchMainCommentList(final Long requestId, final String userId);
    public boolean executeCommentSaving(final CommentModel commentModel);
    public boolean executeCommentDeleting(final Long commentId);
}
