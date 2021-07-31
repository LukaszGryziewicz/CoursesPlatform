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
        addCategory(category);
        //when
        addCourse(category, course);
        //then
        assertThat(courseService.findAllCourses()).contains(course);
        assertThat(categoryService.findAllCategories()).contains(category);
    }

    @Test
    public void shouldReturnAllCourses() {
        //given
        CategoryDTO category = new CategoryDTO("", "");
        CourseDTO course = new CourseDTO("", "");
        addCategory(category);
        addCourse(category, course);
        //when
        List<CourseDTO> allCourses = courseService.findAllCourses();
        //then
        assertThat(allCourses).contains(course);
    }

    @Test
    void shouldReturnLecturesFromCourse() {
        //given
        CategoryDTO category = new CategoryDTO("Abc", "Xyz");
        addCategory(category);
        CourseDTO course = new CourseDTO("Xyz", "Abc");
        CourseDTO addedCourse = addCourse(category, course);
        LectureDTO lecture = createLecture(addedCourse, "PPP", "XXX");
        LectureDTO lecture2 = createLecture(addedCourse, "BBB", "AAA");
        //when
        List<LectureDTO> lecturesOfCourse = courseService.findLecturesOfCourse(course.getTitle());
        //than
        assertThat(lecturesOfCourse).containsExactlyInAnyOrder(lecture, lecture2);
    }

    private CourseDTO addCourse(CategoryDTO category, CourseDTO course) {
        return courseService.add(course, category.getTitle());
    }

    private CategoryDTO addCategory(CategoryDTO category) {
        return categoryService.add(category);
    }

    private LectureDTO createLecture(CourseDTO addedCourse, String title, String description) {
        LectureDTO lecture = new LectureDTO(title, description, BigDecimal.valueOf(1000), 25);
        lectureService.add(lecture, addedCourse.getTitle());
        return lecture;
    }

    @Test
    void shouldThrowExceptionWhenCourseDoesNotExist() {
        //given
        CategoryDTO category = new CategoryDTO("Abc", "Xyz");
        addCategory(category);
        CourseDTO course = new CourseDTO("Xyz", "Abc");
        CourseDTO addedCourse = addCourse(category, course);
        LectureDTO lecture = createLecture(addedCourse, "PPP", "XXX");
        LectureDTO lecture2 = createLecture(addedCourse, "BBB", "AAA");
        //when
        Throwable thrown = catchThrowable(() -> courseService.findLecturesOfCourse("aaaaaa"));
        //than
        assertThat(thrown).isInstanceOf(CourseNotFoundException.class);
    }
}
