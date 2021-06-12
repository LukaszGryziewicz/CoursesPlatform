package com.coursesPlatform;

import com.coursesPlatform.category.Category;
import com.coursesPlatform.category.CategoryRepository;
import com.coursesPlatform.category.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CategoryTest {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @Test
    void shouldAddCategory() {
        //given
        Category category = new Category("");
        //when
        categoryService.add(category);
        //then
        assertThat(categoryRepository.findAll()).contains(category);
    }

    @Test
    void shouldReturnListOfAllCategories() {
        //given
        Category category = new Category("");
        categoryRepository.save(category);
        //when
        List<Category> allCategories = categoryService.findAllCategories();
        //then
        assertThat(allCategories).contains(category);
    }
}
