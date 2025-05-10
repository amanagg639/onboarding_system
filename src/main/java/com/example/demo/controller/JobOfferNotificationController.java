package com.example.demo.controller;

import com.example.demo.listener.JobOfferNotificationListener;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job-offer-notification")
public class JobOfferNotificationController {
    @Autowired
    JobOfferNotificationListener jobOfferNotificationListener;

    @PostMapping("/{id}")
    public ResponseEntity<?> sendNotification(@PathVariable Long id){
        boolean status = jobOfferNotificationListener.processJobOfferNotification(id);
        if(status){
            return ResponseEntity.ok("Notification sent successfully!");
        }
        else return ResponseEntity.badRequest().body("Error sending notification!");
    }
}
