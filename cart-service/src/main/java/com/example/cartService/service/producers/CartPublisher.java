package com.example.cartService.service.producers;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendRequireProductKafka(ProductRequestDto productRequestDto) {
        kafkaTemplate.send("cart-product", productRequestDto);
    }
}
