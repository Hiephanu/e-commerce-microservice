package com.example.cartService.service;

import com.example.cartService.model.entity.CartItem;
import com.example.cartService.repositiory.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public List<CartItem> getAllCartItem(long cartId) {
        List<CartItem> cartItems = cartItemRepository.findAllByCiCartId(cartId);
        return cartItems;
    }
}
