package com.example.productservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    private String p_id;
    private String p_name;
    private double p_price;
    private String p_description;
    private String p_shortDescription;
    private String p_origin;
    private int p_quantity;
    private List<ProductMedia> p_images;
    private List<String> p_categories;
    private List<Specific> p_spec;
    private List<Review> P_reviews;
    private Brand p_brand;
    private int p_views;
    private String p_shop_id;
    private int p_sold;

    @CreatedDate
    @Field("p_created_at")
    private LocalDateTime createdAt;
}
