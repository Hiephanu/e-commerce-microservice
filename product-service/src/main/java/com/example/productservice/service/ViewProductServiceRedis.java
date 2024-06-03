package com.example.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ViewProductServiceRedis {
    @Autowired
    private RedisTemplate<String ,String> redisTemplate;

    public void recordView(String productId){
        redisTemplate.opsForValue().increment("product:" + productId + ":views");
    }

    public int getViews(String productId) {
        String views=  redisTemplate.opsForValue().get("product" + productId + ":views");
        return views != null ? Integer.parseInt(views) : 0;
    }


}
