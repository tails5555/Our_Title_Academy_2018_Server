package io.kang.unit_test.domain_service_unit;

import io.kang.domain.mysql.Category;
import io.kang.dto.mysql.CategoryDTO;
import io.kang.repository.mysql.CategoryRepository;
import io.kang.service.domain_service.implement_object.CategoryServiceImpl;
import io.kang.service.domain_service.interfaces.CategoryService;
import io.kang.test_config.JpaTestConfig;
import io.kang.test_config.RedisTestConfig;
import io.kang.unit_test.testing_singleton.domain_unit.CategoryCreateSingleton;
import io.kang.unit_test.testing_singleton.domain_unit.CategoryUpdateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.CategoryDTOCreateSingleton;
import io.kang.unit_test.testing_singleton.dto_unit.CategoryDTOUpdateSingleton;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, RedisTestConfig.class})
public class CategoryServiceUnitTest {
    private MockMvc mockMvc;

    @InjectMocks
    private CategoryService categoryService = new CategoryServiceImpl();

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.categoryService).build();
    }

    @Test
    public void category_find_all_test(){
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(CategoryUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(categoryService.findAll(), Arrays.asList(CategoryDTOUpdateSingleton.INSTANCE.getInstance()));
    }

    @Test
    public void category_get_one_success_test(){
        when(categoryRepository.existsById(1L)).thenReturn(true);
        when(categoryRepository.getOne(1L)).thenReturn(CategoryUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(categoryService.getOne(1L), CategoryDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void category_get_one_failure_test(){
        when(categoryRepository.existsById(0L)).thenReturn(false);
        Assert.assertEquals(categoryService.getOne(0L), null);
    }

    @Test
    public void category_find_by_id_success_test(){
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(CategoryUpdateSingleton.INSTANCE.getInstance()));
        Assert.assertEquals(categoryService.findById(1L), CategoryDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void category_find_by_id_failure_test(){
        when(categoryRepository.findById(0L)).thenReturn(Optional.empty());
        Assert.assertEquals(categoryService.findById(0L), null);
    }

    @Test
    public void category_create_test(){
        Category createCategory = CategoryCreateSingleton.INSTANCE.getInstance();
        Category afterCategory = CategoryUpdateSingleton.INSTANCE.getInstance();
        when(categoryRepository.save(createCategory)).thenReturn(afterCategory);

        CategoryDTO categoryDTO = categoryService.create(CategoryDTOCreateSingleton.INSTANCE.getInstance());
        Assert.assertTrue(categoryDTO.getId() != null);
    }

    @Test
    public void category_update_test(){
        Category updateCategory = CategoryUpdateSingleton.INSTANCE.getInstance();
        when(categoryRepository.save(updateCategory)).thenReturn(updateCategory);

        CategoryDTO categoryDTO = categoryService.update(CategoryDTOUpdateSingleton.INSTANCE.getInstance());
        Assert.assertEquals(categoryDTO, CategoryDTOUpdateSingleton.INSTANCE.getInstance());
    }

    @Test
    public void category_delete_by_id_test(){
        doNothing().when(categoryRepository).deleteById(1L);
        categoryService.deleteById(1L);
    }

    @Test
    public void category_exists_by_id_success_test(){
        when(categoryRepository.existsById(1L)).thenReturn(true);
        Assert.assertTrue(categoryService.existsById(1L));
    }

    @Test
    public void category_exists_by_id_failure_test(){
        when(categoryRepository.existsById(1L)).thenReturn(false);
        Assert.assertFalse(categoryService.existsById(1L));
    }

    @Test
    public void category_count_test(){
        when(categoryRepository.count()).thenReturn(5L);
        Assert.assertEquals(categoryService.count(), 5L);
    }
}
