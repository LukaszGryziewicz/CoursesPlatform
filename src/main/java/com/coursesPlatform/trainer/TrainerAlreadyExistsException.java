package com.coursesPlatform.trainer;

public class TrainerAlreadyExistsException extends RuntimeException {
    public TrainerAlreadyExistsException() {
        super("Trainer already exists");
    }
}
