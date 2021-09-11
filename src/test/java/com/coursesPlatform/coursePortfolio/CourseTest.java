package com.coursesPlatform.coursePortfolio;

import com.coursesPlatform.exceptions.CourseNotFoundException;
import com.coursesPlatform.exceptions.CourseTitleAlreadyExists;
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
        CategoryDTO category = createCategory("Abc", "Xad");
        CourseDTO course = new CourseDTO("Xyz", "Abc");
        CourseDTO addedCourse = courseService.add(course, category.getTitle());
        LectureDTO lecture = createLecture(addedCourse, "PPP", "XXX");
        LectureDTO lecture2 = createLecture(addedCourse, "BBB", "AAA");
        //when
        List<LectureDTO> lecturesOfCourse = courseService.findLecturesOfCourse(course.getTitle());
        //than
        assertThat(lecturesOfCourse).containsExactlyInAnyOrder(lecture, lecture2);
    }

    private CategoryDTO createCategory(String title, String description) {
        CategoryDTO category = new CategoryDTO(title, description);
        categoryService.add(category);
        return category;
    }

    private LectureDTO createLecture(CourseDTO addedCourse, String title, String description) {
        LectureDTO lecture = new LectureDTO(title, description, BigDecimal.valueOf(1000), 25);
        lectureService.add(lecture, addedCourse.getTitle());
        return lecture;
    }

    @Test
    void shouldThrowExceptionWhenCourseDoesNotExist() {
        //given
        CategoryDTO category = createCategory("Abc", "UAS");
        CourseDTO course = new CourseDTO("Xyz", "Abc");
        CourseDTO addedCourse = courseService.add(course, category.getTitle());
        LectureDTO lecture = createLecture(addedCourse, "PPP", "XXX");
        LectureDTO lecture2 = createLecture(addedCourse, "BBB", "AAA");
        //when
        Throwable thrown = catchThrowable(() -> courseService.findLecturesOfCourse("aaaaaa"));
        //than
        assertThat(thrown).isInstanceOf(CourseNotFoundException.class);
    }

    @Test
    void shouldThrowExceptionWhenCourseTitleAlreadyExist() {
        //given
        CategoryDTO category = new CategoryDTO("Abc", "Xyz");
        categoryService.add(category);
        CourseDTO course = new CourseDTO("Xyz", "Abc");
        courseService.add(course, "Abc");
        CourseDTO course2 = new CourseDTO("Xyz", "Def");
        //when
        Throwable thrown = catchThrowable(() -> courseService.add(course2,"Abc"));
        //than
        assertThat(thrown).isInstanceOf(CourseTitleAlreadyExists.class).hasMessageContaining("Course title already exists");
    }
}
