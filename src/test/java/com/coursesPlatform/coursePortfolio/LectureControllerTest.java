package com.coursesPlatform.coursePortfolio;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class LectureControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LectureService lectureService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CategoryService categoryService;

    @Test
    public void shouldAddLecture() throws Exception {
        //given
        CategoryDTO category = new CategoryDTO("IT", "ABC");
        categoryService.add(category);
        Course course = new Course("Abc", "Xyz");
        courseService.add(course, category.getTitle());
        Lecture lecture = new Lecture("Abc", "Xyz", BigDecimal.ONE, 100);
        String content = objectMapper.writeValueAsString(lecture);
        //expect
        mockMvc.perform(post("/lecture/add/" + course.getTitle())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.title").value(lecture.getTitle()))
                .andExpect(jsonPath("$.description").value(lecture.getDescription()))
                .andExpect(jsonPath("$.price").value(lecture.getPrice()))
                .andExpect(jsonPath("$.duration").value(lecture.getDuration()));
    }

    @Test
    public void shouldReturnAllLectures() throws Exception {
        //given
        CategoryDTO category = new CategoryDTO("IT", "ABC");
        categoryService.add(category);
        Course course = new Course("Abc", "Xyz");
        courseService.add(course, category.getTitle());
        Lecture lecture = new Lecture("Abc", "Xyz", BigDecimal.ONE, 100);
        lectureService.add(lecture, course.getTitle());
        //expect
        mockMvc.perform(get("/lecture/all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].title").value(lecture.getTitle()))
                .andExpect(jsonPath("$[0].description").value(lecture.getDescription()))
                .andExpect(jsonPath("$[0].price").value(lecture.getPrice()))
                .andExpect(jsonPath("$[0].duration").value(lecture.getDuration()));
    }
}
