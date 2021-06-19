package com.coursesPlatform.exceptions;

public class IllegalLengthException extends RuntimeException {


    public IllegalLengthException() {
        super("Text too long !") ;
    }
}
