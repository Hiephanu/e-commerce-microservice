package com.example.cartService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AddToCartRequest {
    private long customerId;
    private String productId;
    private int quantity;
}
