package com.coursesPlatform;

import com.coursesPlatform.category.Category;
import com.coursesPlatform.category.CategoryRepository;
import com.coursesPlatform.category.CategoryService;
import com.coursesPlatform.category.IllegalLengthException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

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
    @Test
    public void shouldThrowExceptionAfterTextIsTooLong() {
        //given
        Category category =  new Category(RandomStringUtils.randomAlphanumeric(300));
        //when
        Throwable thrown =  catchThrowable(() ->categoryService.add(category));
        //then
        assertThat(thrown).isInstanceOf(IllegalLengthException.class)
                .hasMessageContaining("Text too long !");
    }
    @Test
    public void shouldThrowExceptionAboveThreeHundredsLetters() {
        //given
        Category category =  new Category(RandomStringUtils.randomAlphanumeric(301));
        //when
        Throwable thrown = catchThrowable(() ->categoryService.add(category));
        //then
        assertThat(thrown).isInstanceOf(IllegalLengthException.class);
    }
    @Test
    public void shouldSaveCategoryWhenTitleLengthCorrect() {
        //given
        Category category =  new Category(RandomStringUtils.randomAlphanumeric(255));
        //when
            categoryService.add(category);
        //then
        assertThat(categoryRepository.findAll()).contains(category);
    }
}
