package com.example.cartService.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DeleteCartItem {
    private long customerId;
    private String productId;
}
