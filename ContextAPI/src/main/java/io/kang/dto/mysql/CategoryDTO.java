package io.kang.dto.mysql;

import io.kang.domain.mysql.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;

    public static CategoryDTO builtToDTO(Category category){
        return new CategoryDTO(category.getId(), category.getName());
    }

    public static Category builtToDomain(CategoryDTO categoryDTO){
        return new Category(categoryDTO.getId(), categoryDTO.getName());
    }
}
