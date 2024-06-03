package com.example.productservice.controller;

import com.example.productservice.exception.model.InternalServerErrorException;
import com.example.productservice.service.MinioStorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/minio")
@AllArgsConstructor
public class MinioStorageController {
    private final MinioStorageService minioStorageService;

    @PostMapping(value = "/upload" , consumes = {"*/*", MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadImage(@RequestParam("files")  List<MultipartFile> files){
        try {
            for(MultipartFile file : files) {
                System.out.println(file.getOriginalFilename());
                minioStorageService.save(file, file.getOriginalFilename());
            }
            return ResponseEntity.ok(null);
        } catch (Exception e){
            throw  new InternalServerErrorException(e.getMessage());
        }
    }
}
