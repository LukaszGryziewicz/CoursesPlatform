package com.coursesPlatform.coursePortfolio;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TrainingOrder")
public class Order {
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

    public Order() {
    }

    public Order(String orderId, LocalDate orderStartDate, LocalDate orderEndDate, Offer offer, TrainerProjection trainer) {
        this.orderId = orderId;
        this.orderStartDate = orderStartDate;
        this.orderEndDate = orderEndDate;
        this.offer = offer;
        this.trainer = trainer;
    }

    public LocalDate getOrderStartDate() {
        return orderStartDate;
    }

    public void setOrderStartDate(LocalDate orderStartDate) {
        this.orderStartDate = orderStartDate;
    }

    public LocalDate getOrderEndDate() {
        return orderEndDate;
    }

    public void setOrderEndDate(LocalDate orderEndDate) {
        this.orderEndDate = orderEndDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public TrainerProjection getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerProjection trainer) {
        this.trainer = trainer;
    }
}
