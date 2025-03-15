package com.example.trello.Dto.Response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
// data chỉ tạo constructor khi field k phải là notnull hoặc final
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    T data;

    @Builder.Default
    Boolean success = true;

    @Builder.Default
    ErrorResponses error = null;

    @Builder
    @Data
    public static class ErrorResponses {
        Integer code;
        String message;
    }
}
