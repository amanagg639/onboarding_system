package com.example.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String JOB_OFFER_QUEUE = "job.offer.queue";

    @Bean
    public Queue jobOfferQueue() {
        return new Queue(JOB_OFFER_QUEUE, true);
    }
}