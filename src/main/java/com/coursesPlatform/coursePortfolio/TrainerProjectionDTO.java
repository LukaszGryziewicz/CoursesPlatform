package com.coursesPlatform.coursePortfolio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TrainerProjectionDTO {
    private String name;
    private String lastName;
    private String mail;
    private List<LocalDate> unavailableDays = new ArrayList<>();

    @JsonCreator
    public TrainerProjectionDTO(
            @JsonProperty("name") String name,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("mail") String mail) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
    }

    public TrainerProjectionDTO(String name, String lastName, String mail, List<LocalDate> unavailableDays) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.unavailableDays = unavailableDays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public List<LocalDate> getUnavailableDays() {
        return unavailableDays;
    }

    public void setUnavailableDays(List<LocalDate> unavailableDays) {
        this.unavailableDays = unavailableDays;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        TrainerProjectionDTO that = (TrainerProjectionDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, mail);
    }
}
