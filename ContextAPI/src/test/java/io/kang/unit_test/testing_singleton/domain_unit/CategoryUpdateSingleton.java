package io.kang.unit_test.testing_singleton.domain_unit;

import io.kang.domain.Category;

public enum CategoryUpdateSingleton {
    INSTANCE;
    private Category category = new Category(1L, "CATEGORY_NAME01");
    public Category getInstance(){
        if(this.category == null)
            return new Category(1L, "CATEGORY_NAME01");
        return this.category;
    }
}