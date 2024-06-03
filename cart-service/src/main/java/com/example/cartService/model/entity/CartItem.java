package com.example.cartService.model.entity;

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
}
