package com.example.cartService.model.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCartItemSaveDto {
    private String product_id;
    private long cart_id;
    private int quantity;
}
