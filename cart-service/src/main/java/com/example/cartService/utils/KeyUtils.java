package com.example.cartService.utils;

public class KeyUtils {
    public long extractProductId(String key) {
        return  Long.parseLong(key.substring(8));
    }
}
