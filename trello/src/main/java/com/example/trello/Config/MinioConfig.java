package com.example.trello.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;


@Configuration
public class MinioConfig {
    @Value("${spring.minio.url}")
     String minioUrl;

    @Value("${spring.minio.access-key}")
     String minioAccessKey;

    @Value("${spring.minio.secret-key}")
     String minioSecretKey;


    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(
                        minioUrl)
                .credentials(
                        minioAccessKey, 
                        minioSecretKey)
                .build();
    }
}
