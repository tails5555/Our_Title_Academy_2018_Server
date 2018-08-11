package io.kang.service.integrate_service.interfaces;

import io.kang.dto.mysql.RequestDTO;
import io.kang.model.AgreeModel;
import io.kang.model.PaginationModel;
import io.kang.model.RequestModel;
import io.kang.vo.BriefFetchRequestVO;
import io.kang.vo.MainFetchRequestVO;
import io.kang.vo.PaginationVO;

import java.util.List;

public interface RequestFetchService {
    public List<BriefFetchRequestVO> fetchHomeBriefFetchRequests();
    public List<BriefFetchRequestVO> fetchPhotoAgreeBriefRequests();
    public MainFetchRequestVO fetchViewMainFetchRequestVO(final Long requestId, final String userId);
    public PaginationVO fetchCategoryBriefFetchRequests(final Long categoryId, final PaginationModel paginationModel);
    public RequestDTO executeSaveRequest(final RequestModel requestModel);
    public RequestDTO executeRequestAgree(final AgreeModel agreeModel);
    public RequestDTO executeRequestBlocking(final Long requestId);
    public boolean executeDeleteRequest(final Long requestId);
    public void viewPlus(final Long requestId);
}
