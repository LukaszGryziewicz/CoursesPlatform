package com.coursesPlatform.coursePortfolio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    private String mail;
    private boolean occupied;

    Trainer() {
    }

    Trainer(String name, String lastName, String mail) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
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

    boolean isOccupied() {
        return occupied;
    }

    void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Trainer trainer = (Trainer) o;
        return occupied == trainer.occupied && Objects.equals(id, trainer.id) && Objects.equals(name, trainer.name) && Objects.equals(lastName, trainer.lastName) && Objects.equals(mail, trainer.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, mail, occupied);
    }
}
