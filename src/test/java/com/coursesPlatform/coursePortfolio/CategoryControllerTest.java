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

public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private LectureService lectureService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldAddCategory() throws Exception {
        //given
        CategoryDTO category = new CategoryDTO("abc", "def");
        CategoryDTO category2 = new CategoryDTO("xyz", "sadf");
        String content = objectMapper.writeValueAsString(category);
        //expect
        mockMvc.perform(
                post("/category/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.title").value(category.getTitle()))
                .andExpect(jsonPath("$.description").value(category.getDescription()));
        String content1 = objectMapper.writeValueAsString(category2);
        mockMvc.perform(
                post("/category/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content1))
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.title").value(category2.getTitle()))
                .andExpect(jsonPath("$.description").value(category2.getDescription()));
    }


    @Test
    void shouldReturnAllCategories() throws Exception {
        //given
        CategoryDTO category = new CategoryDTO("Cleaning", "abc");
        categoryService.add(category);
        //expect
        this.mockMvc.perform(get("/category/all")).andDo(print())
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(category.getTitle()))
                .andExpect(jsonPath("$[0].description").value(category.getDescription()));

    }
}

