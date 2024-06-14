package com.example.notification_service.dto;

import lombok.Data;

@Data
public class NotificationDto {
        private int id;
        private String title;
        private String content;
        private String userId;
}
