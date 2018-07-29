package io.kang.service.domain_service.interfaces;

import io.kang.dto.ProfileDTO;

import java.util.List;

public interface ProfileService {
    public List<ProfileDTO> findAll();
    public ProfileDTO getOne(final Long id);
    public ProfileDTO findById(final Long id);
    public ProfileDTO findByUserLoginId(final String loginId);
    public ProfileDTO create(final ProfileDTO profileDTO);
    public ProfileDTO update(final ProfileDTO profileDTO);
    public void deleteById(final Long id);
    public boolean existsById(final Long id);
    public long count();
}
