package io.kang.service.domain_service.interfaces;

import io.kang.model.CityModel;
import io.kang.vo.CityVO;
import io.kang.vo.ProfileVO;

import java.util.List;

public interface ProfileService {
    public List<ProfileVO> findAll();
    public ProfileVO getOneVO(final Long id);
    public ProfileVO findByIdVO(final Long id);
    public ProfileVO create(final ProfileVO profileVO);
    public ProfileVO update(final ProfileVO profileVO);
    public void deleteById(final Long id);
    public boolean existsById(final Long id);
    public long count();
}
