package com.example.cartService.service;

import com.example.cartService.exception.NotFoundException;
import com.example.cartService.model.dto.ProductCartItemSaveDto;
import com.example.cartService.model.dto.ProductCartResDto;
import com.example.cartService.model.dto.ProductResponseDto;
import com.example.cartService.model.entity.ActionType;
import com.example.cartService.model.entity.CartItem;
import com.example.cartService.repositiory.CartItemRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    private final RestTemplate restTemplate;
    public List<ProductCartResDto> getAllCartItem(long cartId) {
        List<ProductCartResDto> productCartResDtos = new ArrayList<>();
        List<CartItem> cartItems = cartItemRepository.findAllByCiCartId(cartId);
        for(CartItem cartItem :cartItems) {
            ProductResponseDto productCartDto = restTemplate.getForObject("http://localhost:8080/product/" + cartItem.getCi_productId(), ProductResponseDto.class);
            if(productCartDto != null) {
                ProductCartResDto productCartResDto = ProductCartResDto.builder()
                        .id(cartItem.getCi_id())
                        .quantity(cartItem.getQuantity())
                        .productResponseDto(productCartDto)
                        .build();
            }
            else {
                throw new NotFoundException();
            }
        }

        return productCartResDtos;
    }
    public CartItem getCartItemById(long id) {
        Optional<CartItem> cartItem = cartItemRepository.findById(id);
        if(cartItem.isEmpty()) {
            throw new NotFoundException();
        }
        return cartItem.get();
    }
    public CartItem addToCart(ProductCartItemSaveDto productCartItemSaveDto) {
        CartItem cartItem = CartItem.convertToCartItem(productCartItemSaveDto);
        return cartItemRepository.save(cartItem);
    }
    @Transactional
    public long updateQuantityCartItem(long id, ActionType actionType, int value) {
        CartItem cartItem = getCartItemById(id);
        switch (actionType) {
            case ADD -> {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemRepository.save(cartItem);
            }
            case MINUS -> {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItemRepository.save(cartItem);
            }
            case CHANGE -> {
                cartItem.setQuantity(value);
                cartItemRepository.save(cartItem);
            }
            default -> throw new IllegalArgumentException("Unknown action type: " + actionType);
        }
        return id;
    }
    public void deleteAllCartItem(long cartId) {
        cartItemRepository.deleteByCiCardId(cartId);
    }
    public long deleteCartItemById(long  id) {
        cartItemRepository.deleteById(id);
        return  id;
    }
}
