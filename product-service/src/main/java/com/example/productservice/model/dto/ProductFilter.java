package com.example.productservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductFilter {
    private double minSize;
    private double maxSize;
    private String origin;
    private List<String> category;
}
