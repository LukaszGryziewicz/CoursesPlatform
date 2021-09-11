package com.coursesPlatform.coursePortfolio;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
class TrainerProjection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    private String mail;
    @ElementCollection
    private List<LocalDate> unavailableDays = new ArrayList<>();

    TrainerProjection() {
    }

    TrainerProjection(String name, String lastName, String mail) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
    }

    public TrainerProjection(String name, String lastName, String mail, List<LocalDate> unavailableDays) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.unavailableDays = unavailableDays;
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

    public List<LocalDate> getUnavailableDays() {
        return unavailableDays;
    }

    public void setUnavailableDays(List<LocalDate> unavailableDays) {
        this.unavailableDays = unavailableDays;
    }

    void addUnavailableDays(List<LocalDate> unavailableDay) {
        unavailableDays.addAll(unavailableDay);
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        TrainerProjection trainer = (TrainerProjection) o;
        return Objects.equals(id, trainer.id) && Objects.equals(name, trainer.name) && Objects.equals(lastName, trainer.lastName) && Objects.equals(mail, trainer.mail) && Objects.equals(unavailableDays, trainer.unavailableDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, mail, unavailableDays);
    }
}
