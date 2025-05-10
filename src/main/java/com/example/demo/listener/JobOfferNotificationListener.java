package com.example.demo.listener;


import com.example.demo.entity.Candidate;
import com.example.demo.entity.JobOfferNotification;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JobOfferNotificationListener {
    @Autowired
    private final EmailService emailService;
    @Autowired
    private final CandidateRepository candidateRepository;
    @Autowired
    private final NotificationRepository notificationRepository;


    @RabbitListener(queues = "${rabbitmq.job.offer.queue}")
    public boolean processJobOfferNotification(Long candidateId) {
        try {
            Candidate candidate = candidateRepository.findById(candidateId)
                    .orElseThrow(() -> new RuntimeException("Candidate not found: " + candidateId));

            // Send email
            boolean emailSent = emailService.sendJobOfferEmail(candidate);

            // Save notification
            JobOfferNotification notification = JobOfferNotification.builder()
                    .candidate(candidate)
                    .sent(emailSent)
                    .sentAt(emailSent ? java.time.LocalDateTime.now() : null)
                    .retryCount(0)
                    .build();

            notificationRepository.save(notification);
            return emailSent;
//            log.info("Job offer email sent successfully to candidate: {}", candidateId);
        } catch (Exception e) {
            log.error("Failed to process job offer notification for candidate: {}", candidateId, e);
            // Implement retry logic here
            return false;
        }
    }
}