package com.example.productservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "shop")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shop {
    @Id
    private String s_id;
    private String s_uid;
    private String s_name;
    private String s_description;
    private Address s_address;
    private List<ShopReview> s_reviews;

    @CreatedDate
    @Field("s_created_at")
    private LocalDate createdAt;
}
