package com.coursesPlatform.coursePortfolio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class LectureTest {
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private LectureService lectureService;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void shouldAddLecture() {
        //given
        Course course = new Course("", "");
        Lecture lecture = new Lecture("", "", BigDecimal.ONE, Instant.ofEpochSecond(1));
        courseRepository.save(course);
        //when
        lectureService.add(lecture, course.getTitle());
        //then
        assertThat(lectureRepository.findAll()).contains(lecture);
    }

    @Test
    void shouldFindAllLectures() {
        //given
        Course course = new Course("", "");
        Lecture lecture = new Lecture("", "", BigDecimal.ONE, Instant.ofEpochSecond(1));
        courseRepository.save(course);
        //when
        lectureService.add(lecture, course.getTitle());
        //then
        assertThat(lectureRepository.findAll()).contains(lecture);
    }
}
