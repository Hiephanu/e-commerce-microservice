package com.example.cartService.repositiory;

import com.example.cartService.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCiCartId(long cartId);
}
