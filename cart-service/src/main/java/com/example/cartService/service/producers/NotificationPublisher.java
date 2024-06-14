package com.example.cartService.service.producers;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationPublisher {
    private final KafkaTemplate<String , Object> kafkaTemplate;

    public void sendNotification(long userId) {
        kafkaTemplate.send("notification" + userId, "Checkout success");
    }
}
