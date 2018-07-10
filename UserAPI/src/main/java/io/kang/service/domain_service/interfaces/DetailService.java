package io.kang.service.domain_service.interfaces;

import io.kang.vo.DetailVO;
import io.kang.vo.ProfileVO;

import java.util.List;

public interface DetailService {
    public List<DetailVO> findAll();
    public DetailVO getOneVO(final Long id);
    public DetailVO findByIdVO(final Long id);
    public DetailVO create(final DetailVO detailVO);
    public DetailVO update(final DetailVO detailVO);
    public void deleteById(final Long id);
    public boolean existsById(final Long id);
    public long count();
}
