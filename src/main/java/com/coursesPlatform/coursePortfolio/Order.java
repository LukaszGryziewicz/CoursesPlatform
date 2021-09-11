package com.coursesPlatform.coursePortfolio;

import javax.persistence.*;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderId;
    @OneToOne
    private Offer offer;
    @ManyToOne
    private TrainerProjection trainer;

    public Order() {
    }

    public Order(String orderId, Offer offer, TrainerProjection trainer) {
        this.orderId = orderId;
        this.offer = offer;
        this.trainer = trainer;
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
