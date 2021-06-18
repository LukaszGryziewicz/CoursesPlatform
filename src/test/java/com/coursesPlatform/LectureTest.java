package com.coursesPlatform;

import com.coursesPlatform.category.Category;
import com.coursesPlatform.lecture.Lecture;
import com.coursesPlatform.lecture.LectureRepository;
import com.coursesPlatform.lecture.LectureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class LectureTest {
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private LectureService lectureService;

    @Test
    void shouldAddLecture() {
        //given
        Lecture lecture = new Lecture("", "", BigDecimal.ONE, Instant.ofEpochSecond(1));
        //when
        lectureService.add(lecture);
        //then
        assertThat(lectureRepository.findAll()).contains(lecture);
    }

    @Test
    void shouldFindAllLectures() {
        //given
        Lecture lecture = new Lecture("", "",BigDecimal.ONE,Instant.ofEpochSecond(1));
        //when
        lectureService.add(lecture);
        //then
        assertThat(lectureRepository.findAll()).contains(lecture);
    }
}
