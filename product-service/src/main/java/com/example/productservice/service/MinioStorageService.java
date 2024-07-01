package com.example.productservice.service;

import com.example.productservice.config.MinioConfig;
import com.example.productservice.exception.model.InternalServerErrorException;
import com.example.productservice.model.dto.FileSaveReturn;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MinioStorageService {
    private final MinioClient minioClient;
    private final int expirationTime = 60*60;

    private static String generateUniqueFileName(String originalFileName) {
        return UUID.randomUUID() + originalFileName;
    }

    public String  presignedURL(String bucket, String fileName) {
        try {
            if (bucket == null || fileName == null) {
                throw new IllegalArgumentException("Bucket name and file name must not be null");
            }
            return  minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object(fileName)
//                            .expiry(expirationTime)
                            .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw  new InternalServerErrorException(e.getMessage());
        }
    }

    public FileSaveReturn save(MultipartFile file, String fileName) throws Exception{
        String uniqueFileName = generateUniqueFileName(fileName);
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
