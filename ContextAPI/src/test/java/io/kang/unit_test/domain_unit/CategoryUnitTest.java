package io.kang.unit_test.domain_unit;

import io.kang.domain.Category;
import org.junit.Assert;
import org.junit.Test;

public class CategoryUnitTest {
    private static final long ID = 1L;
    private static final String NAME = "CATEGORY_NAME01";

    @Test
    public void id_getter_and_setter_test(){
        Category category = new Category();
        category.setId(ID);
        long id = category.getId();
        Assert.assertEquals(id, ID);
    }

    @Test
    public void name_getter_and_setter_test(){
        Category category = new Category();
        category.setName(NAME);
        String name = category.getName();
        Assert.assertEquals(name, NAME);
    }

    @Test
    public void equals_test(){
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        Category sameCategory = new Category(ID, NAME);
        Assert.assertEquals(category, sameCategory);
    }

    @Test
    public void to_string_test(){
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        String string = category.toString();
        Assert.assertEquals(string, "Category(id=1, name=CATEGORY_NAME01)");
    }
}
