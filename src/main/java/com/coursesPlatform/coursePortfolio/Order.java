package com.coursesPlatform.coursePortfolio;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TrainingOrder")
class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderId;
    private LocalDate orderStartDate;
    private LocalDate orderEndDate;
    @OneToOne
    private Offer offer;
    @ManyToOne
    private TrainerProjection trainer;

    Order() {
    }

    Order(String orderId, LocalDate orderStartDate, LocalDate orderEndDate, Offer offer, TrainerProjection trainer) {
        this.orderId = orderId;
        this.orderStartDate = orderStartDate;
        this.orderEndDate = orderEndDate;
        this.offer = offer;
        this.trainer = trainer;
    }

    LocalDate getOrderStartDate() {
        return orderStartDate;
    }

    LocalDate getOrderEndDate() {
        return orderEndDate;
    }

    String getOrderId() {
        return orderId;
    }

    Offer getOffer() {
        return offer;
    }

    TrainerProjection getTrainer() {
        return trainer;
    }
}
