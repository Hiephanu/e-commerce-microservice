package com.example.productservice.model.dto;

import com.example.productservice.model.entity.Brand;
import com.example.productservice.model.entity.ProductMedia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResDto {
    private String p_id;
    private String p_name;
    private double p_price;
    private String p_shortDescription;
    private String p_origin;
    private Brand p_brand;
    private int p_quantity;
    private int p_sold;
    private List<ProductMedia> p_images;
    private List<String> p_categories;
}
