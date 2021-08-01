package com.coursesPlatform.coursePortfolio;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {
        super("customer not found");
    }
}
