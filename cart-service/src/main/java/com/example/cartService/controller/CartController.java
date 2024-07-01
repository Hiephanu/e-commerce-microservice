package com.example.cartService.controller;

import com.example.cartService.model.dto.AddToCartRequest;
import com.example.cartService.model.dto.DeleteCartItem;
import com.example.cartService.model.dto.UpdateCartItemRequest;
import com.example.cartService.service.CartRedisService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartRedisService cartRedisService;

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody AddToCartRequest addToCartRequest) {
        try {
            cartRedisService.addProductToCart(addToCartRequest);
            return  ResponseEntity.ok("Success");
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/get-all/{customerId}")
    public  ResponseEntity<?> getAllCartItem(@PathVariable long customerId) {
        try {
            return  ResponseEntity.ok(cartRedisService.getAllProductInCart(customerId));
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.internalServerError().build();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateQuantity(@RequestBody UpdateCartItemRequest updateCartItemRequest) {
        try {
            cartRedisService.updateProductQuantityInCart(updateCartItemRequest.getCustomerId(), updateCartItemRequest.getProductId(), updateCartItemRequest.getQuantity());
            return  ResponseEntity.ok("Success");
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCartItem(@RequestBody DeleteCartItem deleteCartItem) {
        try {
            cartRedisService.removeProductItem(deleteCartItem.getCustomerId(), deleteCartItem.getProductId());
            return  ResponseEntity.ok("Success");
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.internalServerError().build();
        }
    }
    @DeleteMapping("/delete/clear/{customerId}")
    public ResponseEntity<?> clearCart(@PathVariable long customerId) {
        try {
            cartRedisService.clearCart(customerId);
            return  ResponseEntity.ok("Success");
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.internalServerError().build();
        }
    }
}
