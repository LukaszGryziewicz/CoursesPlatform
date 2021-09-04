package com.coursesPlatform.emailSender;

import com.coursesPlatform.coursePortfolio.OfferDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final String sender;
    private final String subject;

    EmailService(
            JavaMailSender mailSender,
            @Value("${spring.mail.username}") String sender,
            @Value("${email.subject}") String subject
    ) {
        this.mailSender = mailSender;
        this.sender = sender;
        this.subject = subject;
    }

    void sendMessage(String email, OfferDTO offerDTO) {
        mailSender.send(setupMail(email, offerDTO));
    }

    private SimpleMailMessage setupMail(String email, OfferDTO offerDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(offerDTO.toString());
        return message;
    }
}
