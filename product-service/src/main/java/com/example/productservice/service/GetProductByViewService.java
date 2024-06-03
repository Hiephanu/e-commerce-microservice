package com.example.productservice.service;

import com.example.productservice.model.entity.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetProductByViewService {
    private final ProductRepository productRepository;

    public List<Product> getProductByView(int page, int perPage) {
        PageRequest pageRequest= PageRequest.of(page, perPage);
        return productRepository.getProductByView(pageRequest);
    }
}
