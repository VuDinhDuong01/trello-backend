package com.example.trello.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.trello.Dto.Response.BaseResponse;
import com.example.trello.Dto.Response.BaseResponse.ErrorResponses;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value= ForbiddenErrorException.class )
    public ResponseEntity<Object> handleAccessDeniedException(ForbiddenErrorException ex) {

        BaseResponse<String> response  = BaseResponse.<String>builder()
        .success(false)
        .data(null)    
        .error(ErrorResponses.builder().message(ex.getMessage()).code(HttpStatus.FORBIDDEN.value()).build()).build();
    
        return new ResponseEntity(response, HttpStatus.FORBIDDEN);
    }
}
