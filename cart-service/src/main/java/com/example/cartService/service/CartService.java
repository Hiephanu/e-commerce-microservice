package com.example.cartService.service;

import com.example.cartService.model.entity.Cart;
import com.example.cartService.model.entity.CartItem;
import com.example.cartService.repositiory.CartRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;


    public Cart createCart(long userId) {
        Cart cart = new Cart();
        cart.setC_userId(userId);
        return  cartRepository.save(cart);
    }

}
