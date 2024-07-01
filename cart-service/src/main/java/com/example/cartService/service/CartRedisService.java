package com.example.cartService.service;

import com.example.cartService.model.dto.AddToCartRequest;
import com.example.cartService.model.dto.ProductCartResDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartRedisService{
    void addProductToCart(AddToCartRequest addToCartRequest);
    void updateProductQuantityInCart(long customerId,String productItemId, int quantity);
    void removeProductItem(long customerId, String productItemId);
    List<ProductCartResDto> getAllProductInCart(long customerId);
    void clearCart(long customerId);
}