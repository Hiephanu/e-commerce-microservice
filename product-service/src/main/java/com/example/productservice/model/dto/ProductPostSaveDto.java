package com.example.productservice.model.dto;

import com.example.productservice.model.entity.Brand;
import com.example.productservice.model.entity.Specific;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductPostSaveDto {
    private String p_name;
    private double p_price;
    private int p_quantity;
    private String p_description;
    private String p_shortDescription;
    private String p_origin;
    private Brand p_brand;
    private List<String> categories;
    private List<Specific> p_spec;
    private String p_shop_id;
}
