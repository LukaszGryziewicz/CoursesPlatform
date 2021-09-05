package com.coursesPlatform.coursePortfolio;

import javax.persistence.*;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Offer offer;
    @ManyToOne
    private Trainer trainer;

    public Order() {
    }

    public Order(Offer offer, Trainer trainer) {
        this.offer = offer;
        this.trainer = trainer;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}
