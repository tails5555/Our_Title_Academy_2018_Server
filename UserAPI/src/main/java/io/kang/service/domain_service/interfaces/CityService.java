package io.kang.service.domain_service.interfaces;

import io.kang.dto.CityDTO;

import java.util.List;

public interface CityService {
    public List<CityDTO> findAll();
    public CityDTO getOne(final Long id);
    public CityDTO findById(final Long id);
    public CityDTO create(final CityDTO cityDTO);
    public CityDTO update(final CityDTO cityDTO);
    public void deleteById(final Long id);
    public boolean existsById(final Long id);
    public long count();
}
