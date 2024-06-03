package com.example.notification_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.JsonMessageConverter;

@Configuration
public class ConverterKafkaConfig {
    @Bean()
    public JsonMessageConverter converterKafka() {
        return new JsonMessageConverter();
    }
}
