package com.example.trello.Service;


import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface MinioService 
{
    List<Map<String, Object>> upload(MultipartFile[] files);
}
