package com.example.productservice.converter;

import com.example.productservice.model.dto.ProductPostSaveDto;
import com.example.productservice.model.dto.ProductResDto;
import com.example.productservice.model.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
@Getter
@Setter
public class ConvertProduct {
    public Product convertToProduct(ProductPostSaveDto productPostSaveDto){
        Product product = new Product();
        product.setP_name(productPostSaveDto.getP_name());
        product.setP_price(productPostSaveDto.getP_price());
        product.setP_description(productPostSaveDto.getP_description());
        product.setP_origin(productPostSaveDto.getP_origin());
        product.setP_categories(productPostSaveDto.getCategories());
        product.setP_shortDescription(productPostSaveDto.getP_shortDescription());
        product.setP_spec(productPostSaveDto.getP_spec());
        product.setP_brand(productPostSaveDto.getP_brand());
        product.setP_quantity(productPostSaveDto.getP_quantity());
        return product;
    }
    public static ProductResDto convertToProductResponseDto(Product product) {
        ProductResDto productResDto = new ProductResDto();
        productResDto.setP_id(product.getP_id());
        productResDto.setP_name(product.getP_name());
        productResDto.setP_price(product.getP_price());
        productResDto.setP_shortDescription(product.getP_shortDescription());
        productResDto.setP_origin(product.getP_origin());
        productResDto.setP_brand(product.getP_brand());
        productResDto.setP_images(product.getP_images());
        productResDto.setP_categories(product.getP_categories());
        productResDto.setP_quantity(product.getP_quantity());
        productResDto.setP_sold(product.getP_sold());
        return  productResDto;
    }
}
