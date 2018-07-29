package io.kang.service.domain_service.interfaces;

import io.kang.dto.AgeDTO;

import java.util.List;

public interface AgeService {
    public List<AgeDTO> findAll();
    public AgeDTO getOne(final Long id);
    public AgeDTO findById(final Long id);
    public AgeDTO create(final AgeDTO ageDTO);
    public AgeDTO update(final AgeDTO ageDTO);
    public void deleteById(final Long id);
    public boolean existsById(final Long id);
    public long count();
}
