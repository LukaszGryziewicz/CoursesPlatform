package com.coursesPlatform.coursePortfolio;

public class TrainerNotFoundException extends RuntimeException {
    public TrainerNotFoundException() {
        super("Trainer not found!");
    }
}
