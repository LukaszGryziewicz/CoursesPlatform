package com.coursesPlatform.exceptions;

public class IllegalDescriptionLengthException extends RuntimeException {
    public IllegalDescriptionLengthException() {
        super("Description too long !");
    }
}
