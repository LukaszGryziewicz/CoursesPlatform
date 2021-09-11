package com.coursesPlatform.coursePortfolio;

public class NoTrainerAvailableException extends RuntimeException {
    public NoTrainerAvailableException() {
        super("No trainer is available at given time");
    }
}
