package io.kang.service.domain_service.interfaces;

import io.kang.dto.mysql.PhotoDTO;

import java.util.List;

public interface PhotoService {
    public List<PhotoDTO> findAll();
    public PhotoDTO getOne(final Long id);
    public PhotoDTO findById(final Long id);
    public PhotoDTO create(final PhotoDTO photoDTO);
    public PhotoDTO update(final PhotoDTO photoDTO);
    public void deleteById(final Long id);
    public boolean existsById(final Long id);
    public long count();
}
