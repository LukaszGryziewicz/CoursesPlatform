package com.coursesPlatform;

import com.coursesPlatform.category.Category;
import com.coursesPlatform.category.CategoryService;
import com.coursesPlatform.category.InMemoryCategoryRepository;
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
    private CategoryService categoryService;
    @Autowired
    private InMemoryCategoryRepository inMemoryCategoryRepository;
    @Test
    public void addNewCategory() {
        //given
        Category category = new Category("");
        //when
        categoryService.addNewCategory(category);
        //then
        assertThat(inMemoryCategoryRepository.getCategoryList()).contains(category);
    }

    @Test
    void shouldReturnAllCategories(){
        //given
        Category category = new Category("");
        //when
        categoryService.addNewCategory(category);
        List<Category> allCategories = categoryService.getAllCategories();
        //then
        assertThat(allCategories).contains(category);

    }
}
