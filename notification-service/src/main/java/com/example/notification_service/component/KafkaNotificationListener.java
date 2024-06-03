package com.example.notification_service.component;

import com.example.notification_service.dto.NotificationDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaNotificationListener {
    @KafkaListener(id = "groupA", topics = {"notification_1"})
    public void listenNotification(NotificationDto notificationDto) {
        System.out.println(notificationDto);
    }
}
