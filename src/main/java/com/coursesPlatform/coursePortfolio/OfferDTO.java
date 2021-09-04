package com.coursesPlatform.coursePortfolio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonCreator
    public OfferDTO(
            @JsonProperty("mail") String mail,
            @JsonProperty("categoryTitle") String categoryTitle,
            @JsonProperty("courseTitle") String courseTitle,
            @JsonProperty("lecturesTitle") List<String> lecturesTitle
    ) {
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
        return "Temat twoich kursów: " + courseTitle + "\n" +
                "Twoje kursy: " + "\n- " + String.join("\n- ", lecturesTitle) + "\n" +
                "Cena: " + summaryPrice + "$" + "\n" +
                "Czas trwania: " + summaryDuration + "h" + "\n" +
                "Zespół INQOO";

    }
}
