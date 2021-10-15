package com.coursesPlatform.coursePortfolio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String mail;
    private String phoneNumber;

    Customer() {
    }

    Customer(long id, String name, String mail, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }

    long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
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

    void update(Customer customer) {
        this.name = customer.getName();
        this.mail = customer.getMail();
        this.phoneNumber = customer.getPhoneNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(mail, customer.mail) && Objects.equals(phoneNumber, customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mail, phoneNumber);
    }
}
