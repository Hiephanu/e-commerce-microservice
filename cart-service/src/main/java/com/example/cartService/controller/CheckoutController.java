package com.example.cartService.controller;

import com.example.cartService.model.dto.CreateOrderReqDto;
import com.example.cartService.service.producers.CheckoutPublisher;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
@AllArgsConstructor
public class CheckoutController {
    private final CheckoutPublisher checkoutPublisher;

    @PostMapping("/send")
    public ResponseEntity<ResponseBody> checkout(CreateOrderReqDto createOrderReqDto) {
        try {
            checkoutPublisher.sendCreateOrderReq(createOrderReqDto);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            throw new InternalError(e.getMessage());
        }
    }
}
