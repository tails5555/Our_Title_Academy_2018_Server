package io.kang.service.integrate_service.interfaces;

import io.kang.model.TitleModel;
import io.kang.vo.MainTitleVO;

import java.util.List;

public interface TitleFetchService {
    public MainTitleVO fetchUserRequestTitle(final Long requestId, final String userId);
    public List<MainTitleVO> fetchMainTitleList(final Long requestId, final String userId);
    public boolean executeTitleSaving(final TitleModel titleModel);
    public boolean executeTitleDeleting(final Long titleId);
}
