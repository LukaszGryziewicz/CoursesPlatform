package com.coursesPlatform;

import com.coursesPlatform.category.Category;
import com.coursesPlatform.category.CategoryService;
import com.coursesPlatform.category.InMemoryCategoryRepository;
import com.coursesPlatform.courses.Course;
import com.coursesPlatform.courses.CourseService;
import com.coursesPlatform.courses.InMemoryCourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CoursesTest {
    @Autowired
    private CourseService courseService;
    @Autowired
    private InMemoryCourseRepository courseRepository;
    @Test
    public void shouldAddNewCourse() {
        //given
        Course course = new Course("","");
        //when
        courseService.addNewCourse(course);
        //then
        assertThat(courseRepository.getCoursesList()).contains(course);
    }
    @Test
    public void shouldReturnAllCourses() {
        //given
        Course course = new Course("","");

        //when
        courseService.addNewCourse(course);

        List<Course> allCourses = courseService.getAllCourses();
        //then
        assertThat(allCourses).containsExactlyInAnyOrder(course);
    }
}
