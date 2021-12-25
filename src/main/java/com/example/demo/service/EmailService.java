package com.example.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailService {

    @Autowired
    private final JavaMailSender emailSender;

    public void sendSampleMessage(String to, String message) {
        SimpleMailMessage simpleMailMessage =new SimpleMailMessage();
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("bangercorental@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("LineUp");
        emailSender.send(simpleMailMessage);


    }
}
