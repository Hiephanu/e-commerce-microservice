package com.example.productservice.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FileSaveReturn {
    private String fileName;
    private String mediaType;
    private String bucket;
}
