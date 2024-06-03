package com.example.productservice.model.entity;

import com.example.productservice.model.dto.FileSaveReturn;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductMedia {
    private String mediaType;
    private String fileName;
    private String bucket;

    public static ProductMedia convertToProductMedia(FileSaveReturn fileSaveReturn) {
        return  ProductMedia.builder()
                .bucket(fileSaveReturn.getBucket())
                .fileName(fileSaveReturn.getFileName())
                .mediaType(fileSaveReturn.getMediaType())
                .build();
    }
}
