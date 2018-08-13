package io.kang.service.integrate_service.interfaces;

import io.kang.vo.BriefFetchRequestVO;
import io.kang.vo.ContextStatisticVO;
import io.kang.vo.MainTitleVO;

import java.util.List;

public interface MyContextService {
    public List<BriefFetchRequestVO> fetchMyValidRequestList(final String userId);
    public List<BriefFetchRequestVO> fetchMyNonValidRequestList(final String userId);
    public List<ContextStatisticVO> fetchMyStatisticRequestList(final String userId);
    public List<ContextStatisticVO> fetchMyStatisticTitleList(final String userId);
    public List<MainTitleVO> fetchMyTitleList(final String userId);
}
