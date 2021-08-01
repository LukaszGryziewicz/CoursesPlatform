package com.coursesPlatform.coursePortfolio;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Category category;
    @OneToOne
    private Course course;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Lecture> lectures = new ArrayList<>();
    private BigDecimal summaryPrice;
    private int summaryDuration;

    Offer() {
    }

    Offer(Category category, Course course, List<Lecture> lectures) {
        this.category = category;
        this.course = course;
        this.lectures = lectures;
    }

    public long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public BigDecimal getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(BigDecimal summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

    public int getSummaryDuration() {
        return summaryDuration;
    }

    public void setSummaryDuration(int summaryDuration) {
        this.summaryDuration = summaryDuration;
    }
}
