package com.coursesPlatform.trainerHR;

public class TrainerNotFoundException extends RuntimeException {
    public TrainerNotFoundException() {
        super("Trainer not found!");
    }
}
