package com.example.productservice.controller;

import com.example.productservice.service.ViewProductServiceRedis;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/view")
@AllArgsConstructor
public class ViewProductController {
    private final ViewProductServiceRedis viewProductServiceRedis;

    @PostMapping("/products/{productId}/view")
    public void recordView(@PathVariable String productId) {
        viewProductServiceRedis.recordView(productId);
    }
}
