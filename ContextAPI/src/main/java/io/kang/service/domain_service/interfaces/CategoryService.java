package io.kang.service.domain_service.interfaces;

import io.kang.dto.mysql.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public List<CategoryDTO> findAll();
    public CategoryDTO getOne(final Long id);
    public CategoryDTO findById(final Long id);
    public CategoryDTO create(final CategoryDTO categoryDTO);
    public CategoryDTO update(final CategoryDTO categoryDTO);
    public void deleteById(final Long id);
    public boolean existsById(final Long id);
    public long count();
}
