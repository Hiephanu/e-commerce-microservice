package com.example.cartService.model.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCartResDto {
    private long productId;
    private int quantity;
}
