package com.example.cartService.service;

import com.example.cartService.exception.NotFoundException;
import com.example.cartService.model.dto.ProductResponseDto;
import com.example.cartService.model.entity.CartItem;
import com.example.cartService.repositiory.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemAvailableService {

    private final CartItemRepository cartItemRepository;
    private final RestTemplate restTemplate;

    public boolean checkQuantityAvailable(long id) {
        Optional<CartItem> cartItem = cartItemRepository.findById(id);

       if(cartItem.isEmpty()) {
           return false;
       } else {
           ProductResponseDto productCartDto = restTemplate.getForObject("http://localhost:8080/product/" + cartItem.get().getCi_productId(), ProductResponseDto.class);
           return productCartDto != null && productCartDto.getQuantity() >= cartItem.get().getQuantity();
       }
    }
}
