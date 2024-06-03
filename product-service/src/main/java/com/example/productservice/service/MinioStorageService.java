package com.example.productservice.service;

import com.example.productservice.config.MinioConfig;
import com.example.productservice.model.dto.FileSaveReturn;
import io.minio.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MinioStorageService {
    private final MinioClient minioClient;

    private static String generateUniqueFileName(String originalFileName) {
        return UUID.randomUUID() + originalFileName;
    }
    public FileSaveReturn save(MultipartFile file, String fileName) throws Exception{
        String uniqueFileName = generateUniqueFileName(fileName);
        System.out.println(uniqueFileName);
        ObjectWriteResponse res = minioClient.putObject(
                PutObjectArgs
                        .builder()
                        .bucket(MinioConfig.BUCKET_NAME)
                        .object(uniqueFileName)
                        .stream(file.getInputStream(),file.getSize(),  5242880)
                        .build()
        );
        return  FileSaveReturn.builder()
                .fileName(uniqueFileName)
                .mediaType(file.getContentType())
                .bucket(MinioConfig.BUCKET_NAME)
                .build();
    }
}
