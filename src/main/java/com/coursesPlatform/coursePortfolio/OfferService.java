package com.coursesPlatform.coursePortfolio;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
class OfferService {

    private OfferDTO convertOfferToDTO(Offer offer) {
        Category category = offer.getCategory();
        Course course = offer.getCourse();
        List<Lecture> lectures = offer.getLectures();
        return new OfferDTO(
                category.getTitle(),
                course.getTitle(),
                lectures.stream().map(Lecture::getTitle).collect(Collectors.toList()),
                offer.getSummaryPrice(),
                offer.getSummaryDuration()
        );
    }

    BigDecimal sumPriceOfLectures(List<LectureDTO> lectures) {
        return lectures.stream()
                .map(LectureDTO::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    int sumDurationOfLectures(List<LectureDTO> lectures) {
        return lectures.stream()
                .mapToInt(LectureDTO::getDuration)
                .sum();
    }
}