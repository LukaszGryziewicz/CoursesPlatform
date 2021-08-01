package com.coursesPlatform.coursePortfolio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class OfferServiceTest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private LectureService lectureService;
    @Autowired
    private OfferService offerService;

    private CategoryDTO createCategory(String title, String description) {
        CategoryDTO category = new CategoryDTO(title, description);
        categoryService.add(category);
        return category;
    }

    private CourseDTO createCourse(CategoryDTO addedCategory, String title, String description) {
        CourseDTO course = new CourseDTO(title, description);
        courseService.add(course, addedCategory.getTitle());
        return course;
    }

    private LectureDTO createLecture(CourseDTO addedCourse, String title, String description, BigDecimal price, int duration) {
        LectureDTO lecture = new LectureDTO(title, description, price, duration);
        lectureService.add(lecture, addedCourse.getTitle());
        return lecture;
    }

    @Test
    void shouldSumPriceOfLectures() {
        //given
        CategoryDTO category = createCategory("Abc", "Xyz");
        CourseDTO course = createCourse(category, "Poi", "Lkj");
        LectureDTO lecture = createLecture(course, "Qwe", "Rty", BigDecimal.valueOf(500), 5);
        LectureDTO lecture2 = createLecture(course, "Mno", "Prs", BigDecimal.valueOf(1000), 10);
        List<LectureDTO> lectureDTOS = Arrays.asList(lecture, lecture2);
        //when
        BigDecimal price = offerService.sumPriceOfLectures(lectureDTOS);
        //then
        assertThat(price).isEqualTo(BigDecimal.valueOf(1500));
    }

    @Test
    public void shouldSumDurationOfLectures() {
        //given
        CategoryDTO category = createCategory("Abc", "Xyz");
        CourseDTO course = createCourse(category, "Poi", "Lkj");
        LectureDTO lecture = createLecture(course, "Qwe", "Rty", BigDecimal.valueOf(500), 5);
        LectureDTO lecture2 = createLecture(course, "Mno", "Prs", BigDecimal.valueOf(500), 10);
        List<LectureDTO> lectureDTOS = Arrays.asList(lecture, lecture2);
        //when
        int duration = offerService.sumDurationOfLectures(lectureDTOS);
        //then
        assertThat(duration).isEqualTo(15);
    }
}
