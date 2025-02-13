package com.example.trello.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trello.Dto.Request.CommentRequest;
import com.example.trello.Dto.Response.BaseResponse;
import com.example.trello.Dto.Validator.ModuleDescriptionApi;
import com.example.trello.Entity.CommentEntity;
import com.example.trello.Service.CommentService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Controller
@RestController
@RequestMapping("/api/v1/")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CommentController {

    CommentService commentService;

    @ModuleDescriptionApi(module = "comment", method = "POST", description = "Tạo comment", name = "", path = "/api/v1/comment", matadataAdmin = false, type = "PRIVATE")
    @PostMapping("comment")
    public BaseResponse<CommentEntity> createColumn(@RequestBody @Valid CommentRequest.createComment body) {
        CommentEntity response = commentService.createComment(body);
        BaseResponse<CommentEntity> result = BaseResponse.<CommentEntity>builder().data(response)
                .build();
        return result;
    }
}
