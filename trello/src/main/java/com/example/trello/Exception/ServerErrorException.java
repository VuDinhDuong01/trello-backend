package com.example.trello.Exception;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServerErrorException extends RuntimeException{
    final HttpStatus httpStatus = HttpStatus.BAD_GATEWAY;
    String message;
}
