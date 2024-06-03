//package com.example.productservice.service;
//
//import com.example.productservice.model.dto.ProductResDto;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.AllArgsConstructor;
//import org.springframework.aop.target.LazyInitTargetSource;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//public class ProductRedisService {
//    private final RedisTemplate<String, Object> redisTemplate;
//    private final ObjectMapper objectMapper;
//
//    public void  clear(){
//
//    }
//    public List<ProductResDto> searchProduct(String keyword, Pageable pageable){
//        return null;
//    }
//    public void saveAllProducts(List<ProductResDto> productResDtos, String keyword, Pageable pageable) {
//
//    }
//}
