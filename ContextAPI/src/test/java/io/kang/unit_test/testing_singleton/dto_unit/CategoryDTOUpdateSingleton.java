package io.kang.unit_test.testing_singleton.dto_unit;

import io.kang.dto.mysql.CategoryDTO;

public enum CategoryDTOUpdateSingleton {
    INSTANCE;
    private CategoryDTO categoryDTO = new CategoryDTO(1L, "CATEGORY_NAME01");
    public CategoryDTO getInstance(){
        if(this.categoryDTO == null)
            return new CategoryDTO(1L, "CATEGORY_NAME01");
        else return this.categoryDTO;
    }
}
