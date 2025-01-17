package com.example.trello.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;


@Configuration
public class MinioConfig {
//      @Value("${spring.minio.url}")
//      String minioUrl;

//      @Value("${spring.minio.access-key}")
//      String minioAccessKey;

//      @Value("${spring.minio.secret-key}")
//      String minioSecretKey;


    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(
                        "http://127.0.0.1:9000")
                .credentials(
                        "cYSU2ex0AvVXWwzmfzag", 
                        "TZE299Vut6sP8IsSKI4n8B0GkvhpGioELe00wiJP")
                .build();
    }
}
