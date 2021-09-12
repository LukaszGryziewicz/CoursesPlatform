package com.coursesPlatform.coursePortfolio;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.BigDecimal.valueOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private LectureService lectureService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderFacade orderFacade;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldAddOrder() throws Exception {
        //given
        CategoryDTO category = new CategoryDTO("IT", "Olej");
        categoryService.add(category);
        CourseDTO course = new CourseDTO("JAVA", "Olej2");
        courseService.add(course, category.getTitle());
        LectureDTO lecture = new LectureDTO("Course", "alalalalalala", valueOf(200), 1);
        LectureDTO lecture1 = new LectureDTO("Course1", "lalalalalalala", valueOf(390), 1);
        lectureService.add(lecture, course.getTitle());
        lectureService.add(lecture1, course.getTitle());
        TrainerProjectionDTO trainer = new TrainerProjectionDTO("Adam", "Dominik", "adam@gmail.com");
        orderFacade.trainerCreated(trainer.getName(), trainer.getLastName(), trainer.getMail());
        List<String> listOfLectures = List.of(lecture, lecture1)
                .stream()
                .map(LectureDTO::getTitle)
                .collect(Collectors.toList());
        List<String> listOfLecturesJson = List.of(lecture, lecture1)
                .stream()
                .map(LectureDTO::getTitle)
                .map(s -> "\"" + s + "\"")
                .collect(Collectors.toList());
        CustomerDTO customer = new CustomerDTO("AdamMięśniak", "zaku2001@interia.pl", "123123123");
        customerService.add(customer);
        OfferDTO offer = new OfferDTO("zaku2001@interia.pl", "IT", "JAVA", listOfLectures);
        OfferDTO createdOffer = offerService.create(offer);
        String content = objectMapper.writeValueAsString(createdOffer);

        //except
        mockMvc.perform(post("/order/" + createdOffer.getOfferId() + "/" +
                LocalDate.of(2021, 3, 26) + "/" +
                LocalDate.of(2021, 3, 27))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.offerId").value(createdOffer.getOfferId()))
                .andExpect(jsonPath("$.mail").value(trainer.getMail()))
                .andExpect(jsonPath("$.categoryTitle").value(category.getTitle()))
                .andExpect(jsonPath("$.courseTitle").value(course.getTitle()))
                .andExpect(jsonPath("$.lecturesTitle").value(listOfLectures))
                .andExpect(jsonPath("$.summaryPrice").value(createdOffer.getSummaryPrice()))
                .andExpect(jsonPath("$.summaryDuration").value(createdOffer.getSummaryDuration()));
    }
}