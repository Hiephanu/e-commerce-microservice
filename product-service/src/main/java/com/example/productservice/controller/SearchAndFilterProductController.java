package com.example.productservice.controller;

import com.example.productservice.converter.ConvertProduct;
import com.example.productservice.exception.model.InternalServerErrorException;
import com.example.productservice.model.dto.ProductFilter;
import com.example.productservice.model.entity.Product;
import com.example.productservice.model.response.ResponseBody;
import com.example.productservice.service.FilterProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchAndFilterProductController {
    private final FilterProductService filterProductService;
    private final ConvertProduct convertProduct;

    @GetMapping("")
    public ResponseEntity<ResponseBody> searchProductByKeyword(@RequestParam String keyword,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "20") int perPage) {
        try {
            List<Product> products = filterProductService.searchProductByKeyword(keyword, page, perPage);
            return  ResponseEntity.ok(new ResponseBody("Success", products.stream().map(convertProduct::convertToProductResponseDto), "SUCCESS"));
        } catch (Exception e) {
            throw   new InternalServerErrorException(e.getMessage());
        }
    }

    @GetMapping("/{category}")
    public ResponseEntity<ResponseBody> findProductByCategory(@PathVariable String category,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "20") int perPage) {
        try {
            List<Product> products= filterProductService.findByCategory(category, page, perPage);
            return  ResponseEntity.ok(new ResponseBody("Success",  products.stream().map(convertProduct::convertToProductResponseDto), "SUCCESS"));
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PostMapping("/filter")
    public ResponseEntity<ResponseBody> filterProduct(@RequestBody ProductFilter productFilter,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int perPage)  {
        try {
            List<Product> products = filterProductService.filterProduct(productFilter, page, perPage);
            return ResponseEntity.ok(new ResponseBody("Success",  products.stream().map(convertProduct::convertToProductResponseDto), "SUCCESS"));
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
