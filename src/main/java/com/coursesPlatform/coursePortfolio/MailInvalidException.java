package com.coursesPlatform.coursePortfolio;

public class MailInvalidException extends RuntimeException{
    public MailInvalidException() {
        super("Mail is invalid");
    }

}
