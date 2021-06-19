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
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    void should2() throws Exception {
        //given
        Category category = new Category("abc", "def");
        Category category1 = new Category("xyz", "sadf");
        //when
        String content = objectMapper.writeValueAsString(category);
        mockMvc.perform(
                post("/category/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andDo(print()).andExpect(status().is(201));
        String content1 = objectMapper.writeValueAsString(category1);
        mockMvc.perform(
                post("/category/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content1)

        ).andDo(print()).andExpect(status().is(201));
        //then
        this.mockMvc.perform(get("/category/all")).andDo(print()).andExpect(status().is(200));
    }


    @Test
    void should() throws Exception {
        //given
        Category category = new Category("Cleaning", "abc");
        Category category2 = new Category("IT", "xyz");
        categoryService.add(category);
        categoryService.add(category2);
        categoryRepository.saveAll(Arrays.asList(category, category2));
        System.out.println("categories:" + categoryRepository.findAll());

        Course course = new Course("Java", "abc");
        Course course2 = new Course("Python", "xyz");
        courseService.add(course, category.getTitle());
        courseService.add(course2, category2.getTitle());
        System.out.println("courses: " + courseRepository.findAll());

        Lecture lecture = new Lecture("Java basics", "abc", BigDecimal.ONE, 1);
        Lecture lecture2 = new Lecture("Python basics", "xyz", BigDecimal.ONE, 1);
        lectureService.add(lecture, course.getTitle());
        lectureService.add(lecture2, course2.getTitle());
        //when
        this.mockMvc.perform(get("/category/all")).andDo(print()).andExpect(status().isOk());
        //then
    }
}

//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Autowired
//    private CourseRepository courseRepository;
//
//    @Autowired
//    private LectureRepository lectureRepository;
//
//    @Autowired
//    private CourseService courseService;
//
//    @Autowired
//    private LectureService lectureService;

//    @Test
//    void shouldReturnAddedCategory() {
//        //given
//        Category category = new Category("Cleaning", "abc");
//        Category category2 = new Category("IT", "xyz");
//        categoryRepository.saveAll(Arrays.asList(category, category2));
//        System.out.println("categories:" + categoryRepository.findAll());
//
//        Course course = new Course("Java", "abc");
//        Course course2 = new Course("Python", "xyz");
//        courseService.add(course, category.getTitle());
//        courseService.add(course2, category2.getTitle());
//        System.out.println("courses: " + courseRepository.findAll());
//
//        Lecture lecture = new Lecture("Java basics", "abc", BigDecimal.ONE, 1);
//        Lecture lecture2 = new Lecture("Python basics", "xyz", BigDecimal.ONE, 1);
//        lectureService.add(lecture, course.getTitle());
//        lectureService.add(lecture2, course2.getTitle());
//
//        //when
//        ResponseEntity<Category[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/category/all", Category[].class);
//        //then
//        assertThat(response.getBody()).containsExactly(category, category2);
//
//    }
//}
