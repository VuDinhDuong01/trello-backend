package com.example.trello.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.trello.Service.MinioService;
import com.example.trello.Util.CustomValidation.ModuleDescriptionApi;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Controller
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileController {
    
    MinioService  minioService;

    @ModuleDescriptionApi(
        type = "PRIVATE",
        matadataAdmin =  false,
        name = "upload file",
        description = "upload file to minio",
        method = "POST",
        module = "File",
        path = "/api/v1/upload"
    )
    @PostMapping("/upload")
    public Object uploadFile(@RequestPart("files") MultipartFile[] files){
        Object response = minioService.upload(files);
        return response;
    }
}
