package com.coursesPlatform.trainerHR;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
class TrainerHR {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String lastName;
    private String mail;
    private String phoneNumber;
    private String biography;

    TrainerHR() {
    }

    TrainerHR(String name, String lastName, String mail, String phoneNumber, String biography) {
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

    void update(TrainerHR trainer) {
        this.mail = trainer.getMail();
        this.phoneNumber = trainer.getPhoneNumber();
        this.biography = trainer.getBiography();
    }
}
