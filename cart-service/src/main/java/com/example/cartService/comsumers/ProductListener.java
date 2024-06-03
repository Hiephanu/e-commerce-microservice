package com.example.cartService.comsumers;

import org.springframework.kafka.annotation.KafkaListener;

public class ProductListener {
    @KafkaListener(id = "group", topics = {""})
    public void listenProduct() {

    }
}
