package com.coursesPlatform.coursePortfolio;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.toList;
import static java.util.stream.LongStream.range;

@Service
class OrderService {

    private final OrderRepository orderRepository;
    private final OfferService offerService;
    private final TrainerService trainerService;

    OrderService(OrderRepository orderRepository, OfferService offerService, TrainerService trainerService) {
        this.orderRepository = orderRepository;
        this.offerService = offerService;
        this.trainerService = trainerService;
    }

    OrderDTO add(String offerId, LocalDate orderStartDate, LocalDate orderEndDate) {
        Offer offerByOfferId = offerService.findOfferEntityByOfferId(offerId);
        Trainer availableTrainer = trainerService.findAvailableTrainer(orderStartDate, orderEndDate);
        Order order = new Order(randomUUID().toString(), offerByOfferId, availableTrainer);
        Order savedOrder = orderRepository.save(order);
        assignUnavailableDaysToTrainer(orderStartDate, orderEndDate, availableTrainer);
        return convertOrderToDto(savedOrder);
    }

    private void assignUnavailableDaysToTrainer(LocalDate orderStartDate, LocalDate orderEndDate, Trainer availableTrainer) {
        long numOfDays = DAYS.between(orderStartDate, orderEndDate);
        List<LocalDate> orderDates = range(0, numOfDays)
                .mapToObj(orderStartDate::plusDays)
                .collect(toList());
        availableTrainer.addUnavailableDays(orderDates);
    }

    private OrderDTO convertOrderToDto(Order order) {
        Offer offer = order.getOffer();
        List<String> listOfLectureTitles = offer.getLectures().stream()
                .map(Lecture::getTitle)
                .collect(toList());
        return new OrderDTO(
                order.getOrderId(),
                order.getTrainer().getMail(),
                offer.getCategory().getTitle(),
                offer.getCourse().getTitle(),
                listOfLectureTitles,
                offer.getSummaryPrice(),
                offer.getSummaryDuration()
        );
    }
}
