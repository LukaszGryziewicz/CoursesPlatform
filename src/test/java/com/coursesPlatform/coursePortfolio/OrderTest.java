package com.coursesPlatform.coursePortfolio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.List.of;
import static java.util.stream.LongStream.range;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class OrderTest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private LectureService lectureService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TrainerProjectionService trainerService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private OrderService orderService;


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

    private CustomerDTO createCustomer(String name, String mail, String phoneNumber) {
        CustomerDTO customer = new CustomerDTO(name, mail, phoneNumber);
        customerService.add(customer);
        return customer;
    }

    @Test
    void shouldCreateOrderWithFreeTrainer() {
        //given
        CustomerDTO customer = createCustomer("Adam Dominik", "adamdominik@gmail.com", "123456789");
        CategoryDTO category = createCategory("Java", "Abc");
        CourseDTO course = createCourse(category, "Basics", "Abc");
        LectureDTO lecture = createLecture(course, "Java util", "Abc", valueOf(100), 10);
        LectureDTO lecture2 = createLecture(course, "Spring", "Abc", valueOf(200), 20);
        List<String> listOfLectures = of(lecture.getTitle(), lecture2.getTitle());
        OfferDTO offer = new OfferDTO(
                customer.getMail(),
                category.getTitle(),
                course.getTitle(),
                listOfLectures
        );
        OfferDTO createdOffer = offerService.create(offer);
        TrainerProjectionDTO trainer1 = trainerService.add("Adam", "Dominik", "adam@dominik.com");
        trainer1.getUnavailableDays().add(LocalDate.of(2021, 9, 12));
        TrainerProjectionDTO trainer2 = trainerService.add("Dawid", "Jamka", "dawid@jamka.com");
        //when
        LocalDate orderStartDate = LocalDate.of(2021, 9, 11);
        LocalDate orderEndDate = LocalDate.of(2021, 9, 13);
        OrderDTO createdOrder = orderService.add(
                createdOffer.getOfferId(),
                orderStartDate,
                orderEndDate
        );
        //then
        assertThat(createdOrder.getMail()).isEqualTo(trainer2.getMail());
        assertThat(createdOrder.getCategoryTitle()).isEqualTo(category.getTitle());
        assertThat(createdOrder.getCourseTitle()).isEqualTo(course.getTitle());
        assertThat(createdOrder.getLecturesTitle()).isEqualTo(listOfLectures);
        assertThat(createdOrder.getSummaryDuration()).isEqualTo(createdOffer.getSummaryDuration());
        assertThat(createdOrder.getSummaryPrice()).isEqualTo(createdOffer.getSummaryPrice());
        long numOfDays = DAYS.between(orderStartDate, orderEndDate);
        LocalDate[] unavailableDays = range(0, numOfDays)
                .mapToObj(orderStartDate::plusDays)
                .toArray(LocalDate[]::new);
        assertThat(trainer2.getUnavailableDays()).containsExactlyInAnyOrder(unavailableDays);
    }
}
