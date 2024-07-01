package com.example.cartService.service.serviceImpl;

import com.example.cartService.model.dto.AddToCartRequest;
import com.example.cartService.model.dto.ProductCartResDto;
import com.example.cartService.service.CartRedisService;
import com.example.cartService.utils.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CartRedisServiceImpl extends BaseRedisServiceImpl implements CartRedisService {
    private KeyUtils keyUtils;

    @Autowired
    public CartRedisServiceImpl(RedisTemplate<String , Object> redisTemplate,
                                HashOperations<String, String, Object> hashOperations,
                                KeyUtils keyUtils) {
        super(redisTemplate, hashOperations);
        this.keyUtils = keyUtils;
    }

    @Override
    public void addProductToCart(AddToCartRequest addToCartRequest) {
        String field = "product:" +  addToCartRequest.getProductId();
        String key = String.valueOf(addToCartRequest.getCustomerId());
        if(hashExits(key, field)) {
            int numberOfProduct =(int) hashGet(key, field);
            numberOfProduct += addToCartRequest.getQuantity();
            hashSet(key, field, numberOfProduct);
        } else {
            hashSet(key, field, addToCartRequest.getQuantity());
        }
    }

    @Override
    public void updateProductQuantityInCart(long customerId,String productItemId, int quantity) {
        String field = "product:" + productItemId;
        String key = String.valueOf(customerId);

        if(hashExits(key, field)) {
            int numberOfProduct =(int) hashGet(key, field);
            numberOfProduct += quantity;
            hashSet(key, field, numberOfProduct);
        }
    }

    @Override
    public void removeProductItem(long customerId, String productItemId) {
        String field = "product:" + productItemId;
        String key = String.valueOf(customerId);
        deleteFieldInHash(key, field);
    }

    @Override
    public List<ProductCartResDto> getAllProductInCart(long customerId){
        String key = String.valueOf(customerId);
        List<ProductCartResDto> productCartResDtos = new ArrayList<>();
        Set<String> setKey = getFieldPrefix(key);
        for(String s : setKey) {
            String id = keyUtils.extractProductId(s);
            ProductCartResDto productCartResDto = new ProductCartResDto();
            productCartResDto.setQuantity((int) hashGet(key, s));
            productCartResDto.setProductId(id);
            productCartResDtos.add(productCartResDto);
        }

        return productCartResDtos;
    }

    @Override
    public void clearCart(long customerId) {
        String key = String.valueOf(customerId);
        delete(key);
    }
}
