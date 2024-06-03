package com.example.productservice.service;

import com.example.productservice.converter.ConvertProduct;
import com.example.productservice.model.dto.ProductResDto;
import com.example.productservice.model.entity.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.repository.SearchHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchProductService {
    private final ProductRepository productRepository;
    private final ConvertProduct convertProduct;

    private final SearchHistoryRepository searchHistoryRepository;

    @Cacheable(value = "products", key = "#keyword + '_' + #page + '_' +#perPage ")
    public List<ProductResDto> searchProductByKeyword(String keyword, int page, int perPage) {
        Pageable pageable = PageRequest.of(page, perPage);
        List<Product> products = productRepository.findByKeyword(keyword, pageable);
        return products.stream().map(ConvertProduct::convertToProductResponseDto).toList();
    }

    @Scheduled(fixedDelay = 15 * 60 *1000)
    public  void clearCache() {
        return;
    }
}
