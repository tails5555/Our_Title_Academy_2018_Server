package io.kang.service.integrate_service.interfaces;

import io.kang.dto.mysql.TitleDTO;
import io.kang.vo.BattleFetchRequestVO;
import io.kang.vo.MainTitleVO;

import java.io.IOException;
import java.util.List;

public interface BattleService {
    public BattleFetchRequestVO fetchCurrentTodayRequest(final String userId) throws IOException;
    public List<MainTitleVO> fetchCurrentTodayTitle(final String userId) throws IOException;
    public TitleDTO fetchUserHasTodayRequestTitle(final String userId) throws IOException;
}
