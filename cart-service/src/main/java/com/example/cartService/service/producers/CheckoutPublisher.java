package com.example.cartService.service.producers;

import com.example.cartService.model.dto.CreateOrderReqDto;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckoutPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendCreateOrderReq(CreateOrderReqDto createOrderReqDto) {
        kafkaTemplate.send("create-order", createOrderReqDto);
    }
}
