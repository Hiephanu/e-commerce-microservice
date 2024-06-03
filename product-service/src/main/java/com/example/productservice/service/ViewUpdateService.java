package com.example.productservice.service;

import com.example.productservice.model.entity.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.bouncycastle.math.ec.custom.sec.SecT113Field;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class ViewUpdateService {
    private final RedisTemplate<String, String> redisTemplate;
    private final ProductRepository productRepository;

    @Scheduled(fixedRate = 4000000)
    public void  updateViewsToDatabase() {
        Set<String> keys=  redisTemplate.keys("product:*:views");
        if(keys != null) {
            for(String key : keys) {
                String productId = key.split(":")[1];

                int views = Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get(key)));

                Product product= productRepository.findById(productId).orElse(new Product());
                product.setP_views(product.getP_views() + views);

                productRepository.save(product);

                redisTemplate.delete(key);
            }
        }
    }
}
