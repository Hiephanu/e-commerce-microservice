package com.example.cartService.utils;

import org.springframework.stereotype.Component;

@Component
public class KeyUtils {
    public String extractProductId(String key) {
        return  key.substring(8);
    }
}
