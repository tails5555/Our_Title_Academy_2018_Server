package io.kang.service.domain_service.interfaces;

import io.kang.dto.mysql.RequestDTO;

import java.util.List;

public interface RequestService {
    public List<RequestDTO> findAll();
    public RequestDTO getOne(final Long id);
    public RequestDTO findById(final Long id);
    public RequestDTO create(final RequestDTO requestDTO);
    public RequestDTO update(final RequestDTO requestDTO);
    public void deleteById(final Long id);
    public boolean existsById(final Long id);
    public long count();
}
