package com.coursesPlatform.coursePortfolio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CourseTest {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @Test
    public void shouldAddCourse() {
        //given
        CategoryDTO category = new CategoryDTO("", "");
        CourseDTO course = new CourseDTO("", "");
        categoryService.add(category);
        //when
        courseService.add(course, category.getTitle());
        //then
        assertThat(courseService.findAllCourses()).contains(course);
        assertThat(categoryService.findAllCategories()).contains(category);
    }

    @Test
    public void shouldReturnAllCourses() {
        //given
        CategoryDTO category = new CategoryDTO("", "");
        CourseDTO course = new CourseDTO("", "");
        categoryService.add(category);
        courseService.add(course,category.getTitle());
        //when
        List<CourseDTO> allCourses = courseService.findAllCourses();
        //then
        assertThat(allCourses).contains(course);
    }
}
