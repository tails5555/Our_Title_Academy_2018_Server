package io.kang.unit_test.testing_singleton.dto_unit;

import io.kang.dto.mysql.CategoryDTO;

public enum CategoryDTOCreateSingleton {
    INSTANCE;
    private CategoryDTO categoryDTO = new CategoryDTO(null, "CATEGORY_NAME01");
    public CategoryDTO getInstance(){
        if(this.categoryDTO == null)
            return new CategoryDTO(null, "CATEGORY_NAME01");
        else return this.categoryDTO;
    }
}
