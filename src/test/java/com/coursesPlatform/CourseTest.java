package com.coursesPlatform;

import com.coursesPlatform.category.Category;
import com.coursesPlatform.category.CategoryRepository;
import com.coursesPlatform.course.Course;
import com.coursesPlatform.course.CourseRepository;
import com.coursesPlatform.course.CourseService;
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

    @Test
    public void shouldAddCourse() {
        //given
        Category category = new Category("", "");
        Course course = new Course("", "");
        categoryRepository.save(category);
        //when
        courseService.add(course, category.getTitle());
        //then
        assertThat(courseRepository.findAll()).contains(course);
        assertThat(category.getCourses()).contains(course);
    }

    @Test
    public void shouldReturnAllCourses() {
        //given
        Category category = new Category("", "");
        Course course = new Course("", "");
        courseRepository.save(course);
        categoryRepository.save(category);
        //when
        List<Course> allCourses = courseService.findAllCourses();
        //then
        assertThat(allCourses).contains(course);
    }
}
