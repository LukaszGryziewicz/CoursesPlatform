package com.coursesPlatform.coursePortfolio;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
class Lecture {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String title;
    @Column(length = 1000)
    private String description;
    private BigDecimal price;
    private int duration;
    @ManyToOne
    private Course course;

    public Lecture() {
    }

    Lecture(String title, String description, BigDecimal price, int duration) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    BigDecimal getPrice() {
        return price;
    }

    void setPrice(BigDecimal price) {
        this.price = price;
    }

    int getDuration() {
        return duration;
    }

    void setDuration(int duration) {
        this.duration = duration;
    }
}
