package com.coursesPlatform.coursePortfolio;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class CourseControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CategoryService categoryService;

    @Test
    void shouldAddCourse() throws Exception {
        //given
        CategoryDTO category = new CategoryDTO("IT", "ABC");
        categoryService.add(category);
        Course course = new Course("Abc", "Xyz");
        String content = objectMapper.writeValueAsString(course);
        //expect
        mockMvc.perform(post("/course/add/" + category.getTitle())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().is(201))
                .andDo(print())
                .andExpect(jsonPath("$.title").value(course.getTitle()))
                .andExpect(jsonPath("$.description").value(course.getDescription()));
    }

    @Test
    void shouldReturnAllCourses() throws Exception {
        //given
        CategoryDTO category = new CategoryDTO("IT", "ABC");
        categoryService.add(category);
        Course course = new Course("Abc", "Xyz");
        courseService.add(course, category.getTitle());
        //expect
        mockMvc.perform(get("/course/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(course.getTitle()))
                .andExpect(jsonPath("$[0].description").value(course.getDescription()));
    }

}
