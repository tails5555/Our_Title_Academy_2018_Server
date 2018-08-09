package io.kang.service.integrate_service.interfaces;

import io.kang.enumeration.Status;

public interface IntegrateEmpathyService {
    public void checkedTitleEmpathy(final Long titleId, final String userId, final Status status);
    public void checkedRequestEmpathy(final Long requestId, final String userId, final Status status);
    public void checkedCommentEmpathy(final Long commentId, final String userId, final Status status);
}
