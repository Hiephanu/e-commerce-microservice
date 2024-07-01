package com.example.productservice.service;

import com.example.productservice.converter.ConvertProduct;
import com.example.productservice.model.dto.ProductResDto;
import com.example.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ViewProductServiceRedis {
    @Autowired
    private RedisTemplate<String ,String> redisTemplate;

    private ProductRepository productRepository;
    private ConvertProduct convertProduct;

    public void recordView(String productId){
        redisTemplate.opsForValue().increment("product:" + productId + ":views");
    }

    public int getViews(String productId) {
        String views=  redisTemplate.opsForValue().get("product:" + productId + ":views");
        return views != null ? Integer.parseInt(views) : 0;
    }

    public List<ProductResDto> getPopularProducts(int page, int perPage) {
        int offset = (page - 1) * perPage;
        Set<String> productIds = getTopProductIds(offset, perPage);

        return productIds.stream()
                .map(productId -> productRepository.findById(productId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(product -> convertProduct.convertToProductResponseDto(product))
                .collect(Collectors.toList());
    }


    private Set<String> getTopProductIds(int offset, int limit) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        return zSetOperations.reverseRange("popularProducts", offset, offset + limit - 1);
    }
}
