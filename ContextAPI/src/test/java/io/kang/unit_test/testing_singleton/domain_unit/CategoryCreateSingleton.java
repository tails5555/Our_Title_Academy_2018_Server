package io.kang.unit_test.testing_singleton.domain_unit;

import io.kang.domain.Category;

public enum CategoryCreateSingleton {
    INSTANCE;
    private Category category = new Category(null, "CATEGORY_NAME01");
    public Category getInstance(){
        if(this.category == null)
            return new Category(null, "CATEGORY_NAME01");
        return this.category;
    }
}
