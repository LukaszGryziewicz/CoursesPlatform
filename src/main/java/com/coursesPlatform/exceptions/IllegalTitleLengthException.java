package com.coursesPlatform.exceptions;

public class IllegalTitleLengthException extends RuntimeException {
    public IllegalTitleLengthException() {
        super("Title too long !");
    }
}
