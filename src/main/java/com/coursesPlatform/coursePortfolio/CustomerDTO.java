package com.coursesPlatform.coursePortfolio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CustomerDTO {
    private final String name;
    private String mail;
    private String phoneNumber;

    @JsonCreator
    public CustomerDTO(
            @JsonProperty("name") String name,
            @JsonProperty("mail") String mail,
            @JsonProperty("phoneNumber") String phoneNumber
    ) {
        this.name = name;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
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

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(mail, that.mail) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mail, phoneNumber);
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}