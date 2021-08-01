package com.coursesPlatform.coursePortfolio;

import com.coursesPlatform.exceptions.CategoryNotFoundException;
import com.coursesPlatform.exceptions.CourseNotFoundException;
import com.coursesPlatform.exceptions.LectureListIsEmptyException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
class OfferService {

    private final OfferRepository offerRepository;
    private final CustomerRepository customerRepository;
    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final LectureRepository lectureRepository;

    OfferService(OfferRepository offerRepository, CustomerRepository customerRepository, CategoryRepository categoryRepository, CourseRepository courseRepository, LectureRepository lectureRepository) {
        this.offerRepository = offerRepository;
        this.customerRepository = customerRepository;
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
        this.lectureRepository = lectureRepository;
    }

    List<OfferDTO> findAllOffers() {
        return offerRepository.findAll()
                .stream()
                .map(this::convertOfferToDTO)
                .collect(toList());
    }

    List<OfferDTO> findAllOffersOfCustomer(String mail) {
        return offerRepository.findByCustomerMail(mail)
                .stream()
                .map(this::convertOfferToDTO)
                .collect(toList());

    }

    @Transactional
    OfferDTO create(OfferDTO offerDTO) {
        Offer offer = offerRepository.save(convertDTOToOffer(offerDTO));
        List<Lecture> lectures = offer.getLectures();
        BigDecimal price = sumPriceOfLectures(lectures);
        int duration = sumDurationOfLectures(lectures);
        offer.setSummaryPrice(price);
        offer.setSummaryDuration(duration);
        offerRepository.save(offer);
        return convertOfferToDTO(offer);
    }

    private BigDecimal sumPriceOfLectures(List<Lecture> lectures) {
        return lectures.stream()
                .map(Lecture::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private int sumDurationOfLectures(List<Lecture> lectures) {
        return lectures.stream()
                .mapToInt(Lecture::getDuration)
                .sum();
    }

    private OfferDTO convertOfferToDTO(Offer offer) {
        Customer customer = offer.getCustomer();
        Category category = offer.getCategory();
        Course course = offer.getCourse();
        List<Lecture> lectures = offer.getLectures();
        return new OfferDTO(
                customer.getMail(),
                category.getTitle(),
                course.getTitle(),
                lectures.stream().map(Lecture::getTitle).collect(toList()),
                offer.getSummaryPrice(),
                offer.getSummaryDuration()
        );
    }

    private Offer convertDTOToOffer(OfferDTO offerDTO) {
        Customer customer = customerRepository.findCustomerByMail(offerDTO.getMail()).orElseThrow(CustomerNotFoundException::new);
        Category category = categoryRepository.findCategoryByTitle(offerDTO.getCategoryTitle()).orElseThrow(CategoryNotFoundException::new);
        Course course = courseRepository.findCourseByTitle(offerDTO.getCourseTitle()).orElseThrow(CourseNotFoundException::new);
        List<Lecture> lectures = findAllLecturesWithGivenMails(offerDTO.getLecturesTitle());
        BigDecimal summaryPrice = offerDTO.getSummaryPrice();
        int summaryDuration = offerDTO.getSummaryDuration();
        return new Offer(
                customer,
                category,
                course,
                lectures,
                summaryPrice,
                summaryDuration
        );
    }

    private List<Lecture> findAllLecturesWithGivenMails(List<String> lectures) {
        List<Lecture> lectureList = lectureRepository.findByTitleIn(lectures);
        if ( lectureList.isEmpty() ) {
            throw new LectureListIsEmptyException();
        }
        return lectureList;
    }
}
