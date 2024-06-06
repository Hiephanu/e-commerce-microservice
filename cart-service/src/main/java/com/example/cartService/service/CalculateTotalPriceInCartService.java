package com.example.cartService.service;

import com.example.cartService.model.dto.ProductCartResDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CalculateTotalPriceInCartService {
    private final CartItemService cartItemService;

    public double calculateTotalPrice(long cartId) {
        List<ProductCartResDto> productCartResDtos = cartItemService.getAllCartItem(cartId);
        double totalPrice =  0.0;
        for(ProductCartResDto resDto : productCartResDtos) {
            totalPrice = totalPrice  + resDto.getProductResponseDto().getPrice()* resDto.getQuantity();
        }
        return  totalPrice;
    }
}
