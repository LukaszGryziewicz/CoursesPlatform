package com.coursesPlatform.coursePortfolio;

public class MailIsAlreadyInUseException extends  RuntimeException{
    public MailIsAlreadyInUseException() {
        super("mail is already in use");
    }
}
