package com.example.productservice.controller;

import com.example.productservice.service.ViewProductServiceRedis;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/view")
@AllArgsConstructor
public class ViewProductController {
    private final ViewProductServiceRedis viewProductServiceRedis;

    @PostMapping("/products/{productId}/view")
    public void recordView(@PathVariable String productId) {
        viewProductServiceRedis.recordView(productId);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getViewProduct(@PathVariable String id) {
        return  ResponseEntity.ok(viewProductServiceRedis.getViews(id));
    }

    @GetMapping("/products/popular")
    public ResponseEntity<?> getPopular(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "5") int perPage) {
        return ResponseEntity.ok(viewProductServiceRedis.getPopularProducts(page, perPage));
    }
}
