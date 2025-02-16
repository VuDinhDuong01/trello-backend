package com.example.trello.Controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trello.Dto.Request.BoardRequest;
import com.example.trello.Dto.Response.BaseResponse;
import com.example.trello.Dto.Validator.ModuleDescriptionApi;
import com.example.trello.Entity.BoardEntity;
import com.example.trello.Service.BoardService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Controller
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BoardsController {
    BoardService boardService;

    @ModuleDescriptionApi(module = "board", method = "POST", description = "Tạo board", name = "", path = "/api/v1/board", matadataAdmin = false, type = "PRIVATE")
    @PostMapping("board")
    public BaseResponse<BoardEntity> createBoard(@RequestBody @Valid BoardRequest.createBoard body) {
        BoardEntity response = boardService.createBoard(body);
        BaseResponse<BoardEntity> result = BaseResponse.<BoardEntity>builder().data(response)
                .build();
        return result;
    }

    @ModuleDescriptionApi(module = "board", method = "DELETE", description = "Xóa board", name = "", path = "/api/v1/board", matadataAdmin = false, type = "PRIVATE")
    @DeleteMapping("board")
    public BaseResponse<String> deleteBoard(@RequestBody @Valid BoardRequest.deleteBoard body) {
        String response = boardService.deleteBoard(body);
        BaseResponse<String> result = BaseResponse.<String>builder().data(response)
                .build();
        return result;
    }

    @ModuleDescriptionApi(module = "board", method = "POST", description = "lấy board", name = "", path = "/api/v1/board", matadataAdmin = false, type = "PRIVATE")
    @PostMapping("board/filter")
    public BaseResponse<Map<String, Object>> getAllBoard(@RequestBody @Valid BoardRequest.getBoard body) {
        Map<String, Object> response = boardService.getBoard(body);
        BaseResponse<Map<String, Object>> result = BaseResponse.<Map<String, Object>>builder().data(response)
                .build();
        return result;
    }

}
