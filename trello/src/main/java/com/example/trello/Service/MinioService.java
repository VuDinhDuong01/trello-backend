package com.example.trello.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.trello.Util.Util;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.http.Method;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MinioService {

    @Value("${spring.minio.bucket-name}")
    String bucketName;

    MinioClient minioClient;

    public List<Map<String, Object>> upload(MultipartFile[] files) {
        String userId = Util.getIdByToken();
        Map<String, Object> mapImage = new HashMap<>();
        List<Map<String, Object>> listMapImage = new ArrayList<>();
        Arrays.asList(files).stream().forEach(file -> {
            String fileName = userId + "/images/" + file.getOriginalFilename();
            try {
                minioClient.putObject(PutObjectArgs.builder()
                        .bucket("trello-bucket")
                        .object(fileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());
                String url = minioClient.getPresignedObjectUrl(
                        GetPresignedObjectUrlArgs.builder().bucket(
                                bucketName).method(Method.GET).expiry(3600)
                                .object(fileName)
                                .extraQueryParams(Map.of("response-content-type", "image/jpg")).build());
                mapImage.put("url", url);
                mapImage.put("type", "image");
                listMapImage.add(mapImage);
            } catch (InvalidKeyException | ErrorResponseException | InsufficientDataException | InternalException
                    | InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException
                    | IllegalArgumentException | IOException e) {
                e.printStackTrace();
            }
        });

        return listMapImage;
    }

}
