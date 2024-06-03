package com.example.productservice.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "search-history")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchHistory {
    @Id
    private String id;
    private String keyword;

    @CreatedDate
    @Field("search_time")
    private LocalDateTime search_time;
}
