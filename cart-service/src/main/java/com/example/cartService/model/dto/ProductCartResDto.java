package com.example.cartService.model.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCartResDto {
    private String productId;
    private int quantity;
}
