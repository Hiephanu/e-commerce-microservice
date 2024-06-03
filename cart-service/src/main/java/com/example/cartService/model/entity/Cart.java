package com.example.cartService.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long c_id;
    private Long c_userId;

    @OneToMany
    private List<CartItem> cartItems;
    private LocalDate c_createAt;

    @PrePersist
    public void prePersist() {
        c_createAt = LocalDate.now();
    }
}
