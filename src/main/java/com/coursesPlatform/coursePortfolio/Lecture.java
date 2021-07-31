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

    public Lecture() {
    }

    Lecture(String title, String description, BigDecimal price, int duration) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
