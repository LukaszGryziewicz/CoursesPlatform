package com.coursesPlatform.trainer;

public class VacationTooLongException extends RuntimeException {
    public VacationTooLongException() {
        super("The vacation period you entered is too long");
    }
}
