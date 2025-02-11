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
import com.example.trello.Dto.Request.ColumnRequest;
import com.example.trello.Dto.Response.BaseResponse;
import com.example.trello.Entity.BoardEntity;
import com.example.trello.Entity.ColumnEntity;
import com.example.trello.Service.ColumnService;
import com.example.trello.Util.CustomValidation.ModuleDescriptionApi;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Controller
@RestController
@RequestMapping("/api/v1/")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ColumnController {

    ColumnService columnService;

    @ModuleDescriptionApi(module = "column", method = "POST", description = "Tạo cột", name = "", path = "/api/v1/column", matadataAdmin = false, type = "PRIVATE")
    @PostMapping("column")
    public BaseResponse<ColumnEntity> createColumn(@RequestBody @Valid ColumnRequest.createColumn body) {
        ColumnEntity response = columnService.createColumn(body);
        BaseResponse<ColumnEntity> result = BaseResponse.<ColumnEntity>builder().data(response)
                .build();
        return result;
    }

    @ModuleDescriptionApi(module = "column", method = "DELETE", description = "Xóa cột", name = "", path = "/api/v1/column", matadataAdmin = false, type = "PRIVATE")
    @DeleteMapping("column")
    public BaseResponse<String> deleteBoard(@RequestBody @Valid ColumnRequest.deleteColumn body) {
        String response = columnService.deleteColumn(body);
        BaseResponse<String> result = BaseResponse.<String>builder().data(response)
                .build();
        return result;
    }

    @ModuleDescriptionApi(module = "column", method = "GET", description = "lấy column", name = "", path = "/api/v1/column", matadataAdmin = false, type = "PRIVATE")
    @GetMapping("column")
    public BaseResponse<Map<String, Object>> getAllBoard(@RequestBody @Valid ColumnRequest.getColumn body) {
        Map<String, Object> response = columnService.getColumn(body);
        BaseResponse<Map<String, Object>> result = BaseResponse.<Map<String, Object>>builder().data(response)
                .build();
        return result;
    }
}
