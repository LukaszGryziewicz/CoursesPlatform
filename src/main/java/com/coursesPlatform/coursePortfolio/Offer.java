package com.coursesPlatform.coursePortfolio;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Customer customer;
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

    Offer(Customer customer, Category category, Course course, List<Lecture> lectures, BigDecimal summaryPrice, int summaryDuration) {
        this.customer = customer;
        this.category = category;
        this.course = course;
        this.lectures = lectures;
        this.summaryPrice = summaryPrice;
        this.summaryDuration = summaryDuration;
    }

    public long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Offer offer = (Offer) o;
        return id == offer.id && summaryDuration == offer.summaryDuration && Objects.equals(customer, offer.customer) && Objects.equals(category, offer.category) && Objects.equals(course, offer.course) && Objects.equals(lectures, offer.lectures) && Objects.equals(summaryPrice, offer.summaryPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, category, course, lectures, summaryPrice, summaryDuration);
    }
}
