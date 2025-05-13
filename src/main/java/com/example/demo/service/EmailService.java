package com.example.demo.service;

import com.example.demo.entity.Candidate;
import com.example.demo.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public boolean sendJobOfferEmail(Candidate candidate) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
             message.setTo(candidate.getEmail());
             message.setSubject("Job Offer");
             message.setText("Congratulations!" + candidate.getFirstName() + "You have been selected as a candidate for the job offer. Please check your email for further details.");
             mailSender.send(message);
             return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendOtpEmail(String email, String otp) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Password Reset OTP");
            message.setText("Your OTP is: " + otp);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
