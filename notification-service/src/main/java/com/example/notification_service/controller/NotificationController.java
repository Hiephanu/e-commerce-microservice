package com.example.notification_service.controller;

import com.example.notification_service.dto.NotificationDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class NotificationController {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/send")
    public ResponseEntity<ResponseBody> sendNotification() {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(1);
        notificationDto.setTitle("Hello");
        notificationDto.setContent("Hello con cac");
        notificationDto.setUserId("?");
        this.kafkaTemplate.send("notification_1", notificationDto);
        return ResponseEntity.ok(null);
    }
}
