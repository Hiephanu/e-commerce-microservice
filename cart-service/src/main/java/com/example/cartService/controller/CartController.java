package com.example.cartService.controller;

import com.example.cartService.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private  final CartService cartService;
    @GetMapping("")
    public ResponseEntity<?> getInventory() {
        return null;
    }

    @PostMapping("")
    public ResponseEntity<?> createCart(@PathVariable long userId) {
        return null;
    }
}
