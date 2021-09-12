package com.coursesPlatform.emailSender;

import com.coursesPlatform.coursePortfolio.OfferDTO;
import org.springframework.stereotype.Component;

@Component
public class EmailFacade {

    private final EmailService emailService;

    public EmailFacade(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendMessage(String email, OfferDTO offerDTO) {
        for(int i=0;i<3;i++){
            emailService.sendMessage(email, offerDTO);
        }
    }
}
