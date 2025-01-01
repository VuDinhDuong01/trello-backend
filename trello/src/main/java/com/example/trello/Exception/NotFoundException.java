package com.example.trello.Exception;
import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class NotFoundException extends RuntimeException{
    final HttpStatus httpStatus= HttpStatus.NOT_FOUND;
    String message; 
}
