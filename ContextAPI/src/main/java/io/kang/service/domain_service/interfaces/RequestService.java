package io.kang.service.domain_service.interfaces;

import io.kang.dto.mysql.CategoryDTO;
import io.kang.dto.mysql.RequestDTO;

import java.util.List;

public interface RequestService {
    public List<RequestDTO> findAll();
    public List<RequestDTO> findAllByOrderByWrittenDateDesc();
    public List<RequestDTO> findByUserIdAndCategoryIsNullOrderByWrittenDateDesc(final String userId);
    public List<RequestDTO> findByUserIdAndCategoryIsNotNullOrderByWrittenDateDesc(final String userId);
    public List<RequestDTO> findByCategoryOrderByWrittenDateDesc(final CategoryDTO categoryDTO);
    public List<RequestDTO> findByCategoryIsNullOrderByWrittenDateDesc();
    public RequestDTO getOne(final Long id);
    public RequestDTO findById(final Long id);
    public RequestDTO create(final RequestDTO requestDTO);
    public RequestDTO update(final RequestDTO requestDTO);
    public void deleteById(final Long id);
    public boolean existsById(final Long id);
    public long count();
}
