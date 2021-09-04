package com.coursesPlatform.trainer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TrainerDTO {
    private final String name;
    private final String lastName;
    private String mail;
    private String phoneNumber;
    private String biography;

    @JsonCreator
    public TrainerDTO(
            @JsonProperty("name") String name,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("mail") String mail,
            @JsonProperty("phoneNumber") String phoneNumber,
            @JsonProperty("biography") String biography
    ) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.biography = biography;
    }

    public TrainerDTO(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        TrainerDTO that = (TrainerDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(mail, that.mail) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(biography, that.biography);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, mail, phoneNumber, biography);
    }
}
