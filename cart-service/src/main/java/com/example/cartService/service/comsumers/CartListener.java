package com.example.cartService.service.comsumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CartListener {
    @KafkaListener(id = "group", topics = {"cart-product"})
    public void listenProduct() {

    }
}
