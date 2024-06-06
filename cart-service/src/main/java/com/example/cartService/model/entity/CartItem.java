package com.example.cartService.model.entity;

import com.example.cartService.model.dto.ProductCartItemSaveDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class CartItem {
    @Id()
    private long ci_id;
    private String ci_productId;

    @ManyToOne
    @JoinColumn(name = "ci_cartId")
    private Cart cart;

    private int quantity;
    private LocalDate ci_createdAt;

    @PrePersist
    public void prePersist() {
        ci_createdAt = LocalDate.now();
    }

    public static CartItem convertToCartItem(ProductCartItemSaveDto productCartItemSaveDto) {
        Cart cart = new Cart();
        cart.setC_id(productCartItemSaveDto.getCart_id());
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setQuantity(productCartItemSaveDto.getQuantity());
        cartItem.setCi_productId(productCartItemSaveDto.getProduct_id());

        return cartItem;
    }
}
