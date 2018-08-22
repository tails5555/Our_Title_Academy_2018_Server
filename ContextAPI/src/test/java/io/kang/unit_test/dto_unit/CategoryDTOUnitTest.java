package io.kang.unit_test.dto_unit;

import io.kang.domain.mysql.Category;
import io.kang.dto.mysql.CategoryDTO;
import io.kang.unit_test.testing_singleton.domain_unit.CategoryUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.CategoryDTOUpdateSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CategoryDTOUnitTest {
    private static final Category category = CategoryUpdateSingleton.INSTANCE.getInstance();
    private static final CategoryDTO categoryDTO = CategoryDTOUpdateSingleton.INSTANCE.getInstance();

    @Test
    public void built_to_dto_test() throws IOException {
        CategoryDTO categoryDTO = CategoryDTO.builtToDTO(this.category);
        Assert.assertEquals(categoryDTO, this.categoryDTO);
    }

    @Test
    public void built_to_domain_test() throws IOException {
        Category category = CategoryDTO.builtToDomain(this.categoryDTO);
        Assert.assertEquals(category, this.category);
    }
}
