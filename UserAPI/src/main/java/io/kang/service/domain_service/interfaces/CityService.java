package io.kang.service.domain_service.interfaces;

import io.kang.model.CityModel;
import io.kang.vo.CityVO;

import java.util.List;

public interface CityService {
    public List<CityVO> findAll();
    public CityVO getOneVO(final Long id);
    public CityVO findByIdVO(final Long id);
    public CityModel getOneModel(final Long id);
    public CityModel findByIdModel(final Long id);
    public CityVO create(final CityModel cityModel);
    public CityVO update(final CityModel cityModel);
    public void deleteById(final Long id);
    public boolean existsById(final Long id);
    public long count();
}
