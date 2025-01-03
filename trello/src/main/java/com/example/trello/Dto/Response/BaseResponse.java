package com.example.trello.Dto.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
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
