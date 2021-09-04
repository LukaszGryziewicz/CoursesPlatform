package com.coursesPlatform.coursePortfolio;

public class MailIsInvalidException extends RuntimeException{
    public MailIsInvalidException() {
        super("Mail is invalid");
    }
}
