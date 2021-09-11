package com.coursesPlatform.coursePortfolio;

public class OfferNotFoundException extends RuntimeException {
    public OfferNotFoundException() {
        super("Offer not found");
    }
}
