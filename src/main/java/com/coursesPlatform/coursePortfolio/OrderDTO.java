package com.coursesPlatform.coursePortfolio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderDTO {
    private String orderId;
    private LocalDate orderStartDate;
    private LocalDate orderEndDate;
    private String offerId;
    private String mail;
    private String categoryTitle;
    private String courseTitle;
    private List<String> lecturesTitle;
    private BigDecimal summaryPrice;
    private int summaryDuration;

    @JsonCreator
    public OrderDTO(
            @JsonProperty("orderId") String orderId,
            @JsonProperty("orderStartDate") LocalDate orderStartDate,
            @JsonProperty("orderEndDate") LocalDate orderEndDate,
            @JsonProperty("offerId") String offerId,
            @JsonProperty("mail") String mail,
            @JsonProperty("categoryTitle") String categoryTitle,
            @JsonProperty("courseTitle") String courseTitle,
            @JsonProperty("lecturesTitle") List<String> lecturesTitle,
            @JsonProperty("summaryPrice") BigDecimal summaryPrice,
            @JsonProperty("summaryDuration") int summaryDuration) {
        this.orderId = orderId;
        this.orderStartDate=orderStartDate;
        this.orderEndDate=orderEndDate;
        this.offerId = offerId;
        this.mail = mail;
        this.categoryTitle = categoryTitle;
        this.courseTitle = courseTitle;
        this.lecturesTitle = lecturesTitle;
        this.summaryPrice = summaryPrice;
        this.summaryDuration = summaryDuration;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public List<String> getLecturesTitle() {
        return lecturesTitle;
    }

    public void setLecturesTitle(List<String> lecturesTitle) {
        this.lecturesTitle = lecturesTitle;
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
