package com.coursesPlatform.coursePortfolio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OfferDTO {
    private String mail;
    private String categoryTitle;
    private String courseTitle;
    private List<String> lecturesTitle = new ArrayList<>();
    private BigDecimal summaryPrice;
    private int summaryDuration;

    public OfferDTO() {
    }

    public OfferDTO(String mail, String categoryTitle, String courseTitle, List<String> lecturesTitle) {
        this.mail = mail;
        this.categoryTitle = categoryTitle;
        this.courseTitle = courseTitle;
        this.lecturesTitle = lecturesTitle;
    }

    public OfferDTO(String mail, String categoryTitle, String courseTitle, List<String> lecturesTitle, BigDecimal summaryPrice, int summaryDuration) {
        this.mail = mail;
        this.categoryTitle = categoryTitle;
        this.courseTitle = courseTitle;
        this.lecturesTitle = lecturesTitle;
        this.summaryPrice = summaryPrice;
        this.summaryDuration = summaryDuration;
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

    @Override
    public String toString() {
        return "OfferDTO{" +
                "categoryTitle='" + categoryTitle + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", lecturesTitle=" + lecturesTitle +
                ", summaryPrice=" + summaryPrice +
                ", summaryDuration=" + summaryDuration +
                '}';
    }
}
