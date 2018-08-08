package io.kang.service.integrate_service.interfaces;

import io.kang.vo.MainFetchRequestVO;
import io.kang.vo.RankFetchRequestVO;

import java.util.List;

public interface RankService {
    public List<RankFetchRequestVO> fetchCurrentRanking();
    public List<MainFetchRequestVO> fetchMainCurrentRanking();
}
