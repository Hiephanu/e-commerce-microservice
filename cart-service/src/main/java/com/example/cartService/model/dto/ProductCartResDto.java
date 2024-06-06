package com.example.cartService.model.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCartResDto {
    private long id;
    private int quantity;
    private ProductResponseDto productResponseDto;

}
