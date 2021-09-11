package com.coursesPlatform.coursePortfolio;

import com.coursesPlatform.exceptions.LectureTitleAlreadyExists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
@Transactional
public class LectureTest {
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private LectureService lectureService;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CategoryService categoryService;


    @Test
    void shouldAddLecture() {
        //given
        Course course = new Course("", "");
        LectureDTO lectureDTO = new LectureDTO("", "", BigDecimal.ONE, 1);
        courseRepository.save(course);
        //when
        lectureService.add(lectureDTO, course.getTitle());
        //then
        assertThat(lectureService.findAllLectures()).containsExactlyInAnyOrder(lectureDTO);
    }

    @Test
    void shouldFindAllLectures() {
        //given
        Course course = new Course("", "");
        LectureDTO lectureDTO = new LectureDTO("", "", BigDecimal.ONE, 1);
        courseRepository.save(course);
        //when
        lectureService.add(lectureDTO, course.getTitle());
        //then
        assertThat(lectureService.findAllLectures()).contains((lectureDTO));
    }

    @Test
    void shouldThrowExceptionWhenLectureTitleAlreadyExist() {
        CategoryDTO category = new CategoryDTO("Abc", "Xyz");
        categoryService.add(category);
        CourseDTO course = new CourseDTO("Xyz", "Abc");
        courseService.add(course, "Abc");
        LectureDTO lecture = new LectureDTO("123", "asd", BigDecimal.ONE, 1);
        lectureService.add(lecture, course.getTitle());
        LectureDTO lecture2 = new LectureDTO("123", "def", BigDecimal.ONE, 2);
        //when
        Throwable thrown = catchThrowable(() -> lectureService.add(lecture2, course.getTitle()));
        //than
        assertThat(thrown).isInstanceOf(LectureTitleAlreadyExists.class).hasMessageContaining("Lecture title already exists");
    }
}
