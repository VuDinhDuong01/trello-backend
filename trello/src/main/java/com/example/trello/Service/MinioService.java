package com.example.trello.Service;

import org.springframework.stereotype.Service;

import io.minio.MinioClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MinioService {
    MinioClient minioClient;
}
