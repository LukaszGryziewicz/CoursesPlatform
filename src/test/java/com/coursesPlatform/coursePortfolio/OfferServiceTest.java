package com.coursesPlatform.coursePortfolio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static java.util.List.of;
import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
@Transactional
class OfferServiceTest {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private LectureService lectureService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private CustomerService customerService;

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
    void shouldCreateOffer() {
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
        //when
        offerService.create(offer);
        //then
        List<OfferDTO> allOffers = offerService.findAllOffers();
        OfferDTO offerFromDB = allOffers.get(0);
        assertThat(allOffers.size()).isEqualTo(1);
        assertThat(offerFromDB.getMail()).isEqualTo(customer.getMail());
        assertThat(offerFromDB.getCategoryTitle()).isEqualTo(category.getTitle());
        assertThat(offerFromDB.getCourseTitle()).isEqualTo(course.getTitle());
        assertThat(offerFromDB.getLecturesTitle()).isEqualTo(listOfLectures);
        assertThat(offerFromDB.getSummaryPrice()).isEqualTo(valueOf(300));
        assertThat(offerFromDB.getSummaryDuration()).isEqualTo(30);
    }

    @Test
    void shouldReturnAllOffersOfCustomer() {
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
        offerService.create(offer);

        CustomerDTO customer2 = createCustomer("Lukasz Gryziewicz", "lukasz@gmail.com", "987654321");
        CategoryDTO category2 = createCategory("Python", "Abc");
        CourseDTO course2 = createCourse(category, "Advanced", "Abc");
        LectureDTO lecture3 = createLecture(course, "Python gui", "Abc", valueOf(100), 10);
        LectureDTO lecture4 = createLecture(course, "Django", "Abc", valueOf(200), 20);
        List<String> listOfLectures2 = of(lecture3.getTitle(), lecture4.getTitle());
        OfferDTO offer2 = new OfferDTO(
                customer2.getMail(),
                category2.getTitle(),
                course2.getTitle(),
                listOfLectures2
        );
        offerService.create(offer2);
        //when
        List<OfferDTO> allOffersOfCustomer = offerService.findAllOffersOfCustomer(customer.getMail());
        //then
        OfferDTO offerFromDB = allOffersOfCustomer.get(0);
        assertThat(allOffersOfCustomer.size()).isEqualTo(1);
        assertThat(offerFromDB.getMail()).isEqualTo(customer.getMail());
        assertThat(offerFromDB.getCategoryTitle()).isEqualTo(category.getTitle());
        assertThat(offerFromDB.getCourseTitle()).isEqualTo(course.getTitle());
        assertThat(offerFromDB.getLecturesTitle()).isEqualTo(listOfLectures);
        assertThat(offerFromDB.getSummaryPrice()).isEqualTo(valueOf(300));
        assertThat(offerFromDB.getSummaryDuration()).isEqualTo(30);
    }

    @Test
    public void shouldFindOfferByOfferId() {
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
        OfferDTO offerDTO = offerService.create(offer);
        //when
        Offer offerByOfferId = offerService.findOfferEntityByOfferId(offerDTO.getOfferId());
        //then
        assertThat(offerByOfferId.getOfferId()).isEqualTo(offerDTO.getOfferId());
        assertThat(offerByOfferId.getCategory().getTitle()).isEqualTo(offerDTO.getCategoryTitle());
        assertThat(offerByOfferId.getCourse().getTitle()).isEqualTo(offerDTO.getCourseTitle());
        List<String> lectureTitles = offerByOfferId.getLectures().stream()
                .map(Lecture::getTitle).collect(toList());
        assertThat(lectureTitles).isEqualTo(offerDTO.getLecturesTitle());
        assertThat(offerByOfferId.getSummaryDuration()).isEqualTo(offerDTO.getSummaryDuration());
        assertThat(offerByOfferId.getSummaryPrice()).isEqualTo(offerDTO.getSummaryPrice());
    }

    @Test
    public void shouldThrowExceptionWhenOfferIsNotFound() {
        String randomId = randomUUID().toString();
        //when
        Throwable thrown = catchThrowable(() -> offerService.findOfferEntityByOfferId(randomId));
        //then
        assertThat(thrown).isInstanceOf(OfferNotFoundException.class);
    }
}
