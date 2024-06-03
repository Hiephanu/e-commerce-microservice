package com.example.productservice.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@NoArgsConstructor
@AllArgsConstructor
public class MinioConfig {
    public static final String BUCKET_NAME = "product";

    @Value("${spring.minio.url}")
    private String minioUrl;
    @Value("${spring.minio.accessKey}")
    private String minioAccessKey;
    @Value("${spring.minio.secretKey}")
    private String minioSecretKey;

    @Bean
    public MinioClient minioClient() throws Exception {
        MinioClient client = MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(minioAccessKey,minioSecretKey)
                .build();
        if(!client.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build())){
            client.makeBucket(
                    MakeBucketArgs
                            .builder()
                            .bucket(BUCKET_NAME)
                            .build()
            );
        }
        return client;
    }
}
