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
    Error error = null;

    public static class Error{
        Integer code;
        String message;
    }
}
