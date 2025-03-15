package com.example.trello.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.trello.Config.CORSConfig;
import com.example.trello.Dto.Response.BaseResponse;
import com.example.trello.Dto.Response.BaseResponse.ErrorResponses;
import com.example.trello.Util.ErrorCode;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = ForbiddenErrorException.class)
    // object cần cụ thể
    public ResponseEntity<Object> handleAccessDeniedException(ForbiddenErrorException ex) {

        BaseResponse<String> response = BaseResponse.<String>builder()
                .success(false)
                .data(null)
                .error(ErrorResponses.builder().message(ex.getMessage()).code(HttpStatus.FORBIDDEN.value()).build())
                .build();
        return new ResponseEntity(response, HttpStatus.FORBIDDEN);
        // hoặc return ResponseEntity.badRequest().body(respone)
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<BaseResponse<Object>> handleRuntimeException(AppException ex) {
        // trả về đúng các thuộc tính trong enum đã định nghĩa
        ErrorCode errorCode = ex.getErrorCode();
        BaseResponse<Object> response = BaseResponse.<Object>builder()
                .success(false)
                .data(null)
                .error(ErrorResponses.builder().message(errorCode.getMessage()).code(errorCode.getCode()).build())
                .build();
        return ResponseEntity.badRequest().body(response);
    }
    // ngoài những exception thì bắt 1 exception chung
    // @ExceptionHandler(value = Exception.class)
    // public ResponseEntity<Object> handleException(Exception ex){

    // }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<Object> handle(MethodArgumentNotValidException ex) {
        // laays ra cái message
        String enumKey = ex.getFieldError().getDefaultMessage();
        // lúc này laaij lấy các thuộc tính của enum
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);
    }
}
