package com.example.cartService.model.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
    private String id;
    private String name;
    private String title;
    private double price;
    private int quantity;
}
