package com.example.trello.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;


@Configuration
public class MinioConfig {
    @Value("${spring.minio.url}")
    private String minioUrl;

    @Value("${spring.minio.access-key}")
    private String minioAccessKey;

    @Value("${spring.minio.secret-key}")
    private String minioSecretKey;

    @Value("${spring.minio.bucket-name}")
    private String minioBucketName;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(
                        minioAccessKey, 
                        minioSecretKey)
                .build();
    }
}
