package com.coursesPlatform;

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

    @Test
    public void shouldAddCourse() {
        //given
        Course course= new Course("","");
        //when
        courseService.add(course);
        //then
        assertThat(courseRepository.findAll()).contains(course);
    }

    @Test
    public void shouldReturnAllCourses() {
        //given
        Course course= new Course("","");
        courseRepository.save(course);
        //when
        List<Course> allCourses = courseService.findAllCourses();
        //then
        assertThat(allCourses).contains(course);
    }
}
