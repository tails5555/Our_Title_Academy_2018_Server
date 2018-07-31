package io.kang.service.domain_service.implement_object;

import io.kang.domain.mysql.Category;
import io.kang.dto.mysql.CategoryDTO;
import io.kang.repository.mysql.CategoryRepository;
import io.kang.service.domain_service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> findAll(){
        return categoryRepository.findAll()
                .stream().map(category -> CategoryDTO.builtToDTO(category))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getOne(final Long id) {
        if(categoryRepository.existsById(id))
            return CategoryDTO.builtToDTO(categoryRepository.getOne(id));
        else return null;
    }

    @Override
    public CategoryDTO findById(final Long id) {
        Optional<Category> tmpCategory = categoryRepository.findById(id);
        if(tmpCategory.isPresent())
            return CategoryDTO.builtToDTO(tmpCategory.get());
        else return null;
    }

    @Override
    public CategoryDTO create(final CategoryDTO categoryDTO) {
        Category createCategory = categoryRepository.save(CategoryDTO.builtToDomain(categoryDTO));
        if(createCategory.getId() != null) return CategoryDTO.builtToDTO(createCategory);
        else return null;
    }

    @Override
    public CategoryDTO update(final CategoryDTO categoryDTO) {
        Category updateCategory = categoryRepository.save(CategoryDTO.builtToDomain(categoryDTO));
        return CategoryDTO.builtToDTO(updateCategory);
    }

    @Override
    public void deleteById(final Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean existsById(final Long id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public long count() {
        return categoryRepository.count();
    }
}
