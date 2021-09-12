package com.coursesPlatform.trainer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String lastName;
    private String mail;
    private String phoneNumber;
    private String biography;
    @ElementCollection
    private final List<LocalDate> vacations = new ArrayList<>();

    Trainer() {
    }

    Trainer(String name, String lastName, String mail, String phoneNumber, String biography) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.biography = biography;
    }

    long getId() {
        return id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String getMail() {
        return mail;
    }

    void setMail(String mail) {
        this.mail = mail;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    String getBiography() {
        return biography;
    }

    void setBiography(String biography) {
        this.biography = biography;
    }

    List<LocalDate> getVacations() {
        return vacations;
    }

    void addVacation(List<LocalDate> listOfVacations) {
        vacations.addAll(listOfVacations);
    }

    void update(Trainer trainer) {
        this.name = trainer.getName();
        this.lastName = trainer.getLastName();
        this.mail = trainer.getMail();
        this.phoneNumber = trainer.getPhoneNumber();
        this.biography = trainer.getBiography();
    }
}
