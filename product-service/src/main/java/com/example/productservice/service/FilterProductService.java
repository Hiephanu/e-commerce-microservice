package com.example.productservice.service;

import com.example.productservice.model.dto.ProductFilter;
import com.example.productservice.model.entity.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FilterProductService {
    private final ProductRepository productRepository;

    public List<Product> searchProductByKeyword(String keyword, int page, int perPage) {
        PageRequest pageRequest = PageRequest.of(page, perPage);
        return productRepository.findByKeyword(keyword, pageRequest);
    }
    public List<Product> filterProduct(ProductFilter productFilter, int page, int perPage) {
        PageRequest pageRequest = PageRequest.of(page, perPage);
        return  productRepository.filterProduct(productFilter, pageRequest);
    }
    public List<Product> findByCategory(String category, int page, int perPage) {
        PageRequest pageRequest = PageRequest.of(page, perPage);
        return productRepository.findByCategory(category, pageRequest);
    }

}
