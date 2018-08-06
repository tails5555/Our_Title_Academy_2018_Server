package io.kang.service.integrate_service.interfaces;

import io.kang.model.PaginationModel;
import io.kang.vo.BriefFetchRequestVO;
import io.kang.vo.MainFetchRequestVO;
import io.kang.vo.PaginationVO;

import java.util.List;

public interface RequestFetchService {
    public List<BriefFetchRequestVO> fetchHomeBriefFetchRequests();
    public MainFetchRequestVO fetchViewMainFetchRequestVO(final Long requestId, final String userId);
    public PaginationVO fetchCategoryBriefFetchRequests(final Long categoryId, final PaginationModel paginationModel);
    public void viewPlus(final Long requestId);
}
