package io.kang.service.integrate_service.interfaces;

import io.kang.vo.SearchResultVO;

import java.util.List;

public interface TotalSearchService {
    public List<SearchResultVO> fetchSearchResultList(final String keyword);
}
