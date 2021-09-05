package com.coursesPlatform.coursePortfolio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TrainerDTO {
    private String name;
    private String lastName;
    private String mail;

    @JsonCreator
    public TrainerDTO(
            @JsonProperty("name") String name,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("mail") String mail) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
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

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        TrainerDTO that = (TrainerDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, mail);
    }
}
