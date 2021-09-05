package com.coursesPlatform.coursePortfolio;

import com.coursesPlatform.exceptions.CategoryNotFoundException;
import com.coursesPlatform.exceptions.IllegalLengthException;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
@Transactional
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CourseService courseService;


    @Test
    void shouldAddCategory() {
        //given
        CategoryDTO category = new CategoryDTO("", "");
        //when
        categoryService.add(category);
        //then
        assertThat(categoryService.findAllCategories()).containsExactlyInAnyOrder(category);
    }

    @Test
    void shouldReturnListOfAllCategories() {
        //given
        CategoryDTO category = new CategoryDTO("", "");
        categoryService.add(category);
        //when
        List<CategoryDTO> allCategories = categoryService.findAllCategories();
        //then
        Assertions.assertThat(allCategories).contains(category);
    }

    @Test
    public void shouldThrowExceptionAfterTextIsTooLong() {
        //given
        CategoryDTO category = new CategoryDTO(RandomStringUtils.randomAlphanumeric(200), "");
        //when
        Throwable thrown = catchThrowable(() -> categoryService.add(category));
        //then
        assertThat(thrown).isInstanceOf(IllegalLengthException.class)
                .hasMessageContaining("Text too long !");
    }

    @Test
    public void shouldThrowExceptionAboveThreeHundredsLetters() {
        //given
        CategoryDTO category = new CategoryDTO(RandomStringUtils.randomAlphanumeric(301), "");
        //when
        Throwable thrown = catchThrowable(() -> categoryService.add(category));
        //then
        assertThat(thrown).isInstanceOf(IllegalLengthException.class);
    }

    @Test
    public void shouldSaveCategoryWhenTitleLengthCorrect() {
        //given
        CategoryDTO category = new CategoryDTO(RandomStringUtils.randomAlphanumeric(255), "");
        //when
        categoryService.add(category);
        //then
        assertThat(categoryService.findAllCategories()).containsExactlyInAnyOrder(category);
    }

    @Test
    void shouldReturnCoursesAssignedToCategory() {
        //given
        CategoryDTO category = new CategoryDTO("Abc", "Xyz");
        CourseDTO course = new CourseDTO("Bca", "Poq");
        CourseDTO course2 = new CourseDTO("Aph", "Lkk");
        categoryService.add(category);
        courseService.add(course, category.getTitle());
        courseService.add(course2, category.getTitle());
        //when
        List<CourseDTO> categoriesOfCourse = categoryService.findCategoriesOfCourse(category.getTitle());
        //than
        assertThat(categoriesOfCourse).contains(course, course2);
    }

    @Test
    void shouldThrowExceptionWhenCategoryDoesNotExist() {
        //given
        CategoryDTO category = new CategoryDTO("Abc", "Xyz");
        categoryService.add(category);
        CourseDTO course = new CourseDTO("Bca", "Poq");
        CourseDTO course2 = new CourseDTO("Aph", "Lkk");
        courseService.add(course, category.getTitle());
        courseService.add(course2, category.getTitle());
        //when
        Throwable thrown = catchThrowable(() -> categoryService
                .findCategoriesOfCourse("dupa"));
        //than
        assertThat(thrown).isInstanceOf(CategoryNotFoundException.class);
    }
}
