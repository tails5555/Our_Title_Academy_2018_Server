package io.kang.service.domain_service.interfaces;

import io.kang.dto.DetailDTO;

import java.util.List;

public interface DetailService {
    public List<DetailDTO> findAll();
    public DetailDTO getOne(final Long id);
    public DetailDTO findById(final Long id);
    public DetailDTO findByLoginId(final String loginId);
    public DetailDTO findByNameAndEmail(final String name, final String email);
    public DetailDTO create(final DetailDTO detailDTO);
    public DetailDTO update(final DetailDTO detailDTO);
    public void deleteById(final Long id);
    public boolean existsById(final Long id);
    public long count();
}
