package com.coursesPlatform.coursePortfolio;

import com.coursesPlatform.exceptions.CourseNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
@Transactional
public class CourseTest {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LectureService lectureService;

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
        courseService.add(course, category.getTitle());
        //when
        List<CourseDTO> allCourses = courseService.findAllCourses();
        //then
        assertThat(allCourses).contains(course);
    }

    @Test
    void shouldReturnLecturesFromCourse() {
        //given
        CategoryDTO category = new CategoryDTO("Abc", "Xyz");
        categoryService.add(category);
        CourseDTO course = new CourseDTO("Xyz", "Abc");
        CourseDTO addedCourse = courseService.add(course, category.getTitle());
        LectureDTO lecture = new LectureDTO("PPP", "XXX", BigDecimal.valueOf(1000), 25);
        LectureDTO lecture2 = new LectureDTO("BBB", "AAA", BigDecimal.valueOf(1000), 25);
        lectureService.add(lecture, addedCourse.getTitle());
        lectureService.add(lecture2, addedCourse.getTitle());
        //when
        List<LectureDTO> lecturesOfCourse = courseService.findLecturesOfCourse(course.getTitle());
        //than
        assertThat(lecturesOfCourse).containsExactlyInAnyOrder(lecture, lecture2);
    }

    @Test
    void shouldThrowExceptionWhenCourseDoesNotExist() {
        //given
        CategoryDTO category = new CategoryDTO("Abc", "Xyz");
        categoryService.add(category);
        CourseDTO course = new CourseDTO("Xyz", "Abc");
        CourseDTO addedCourse = courseService.add(course, category.getTitle());
        LectureDTO lecture = new LectureDTO("PPP", "XXX", BigDecimal.valueOf(1000), 25);
        LectureDTO lecture2 = new LectureDTO("BBB", "AAA", BigDecimal.valueOf(1000), 25);
        lectureService.add(lecture, addedCourse.getTitle());
        lectureService.add(lecture2, addedCourse.getTitle());
        //when
        Throwable thrown = catchThrowable(() -> courseService.findLecturesOfCourse("aaaaaa"));
        //than
        assertThat(thrown).isInstanceOf(CourseNotFoundException.class);
    }
}
