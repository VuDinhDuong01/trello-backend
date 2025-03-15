package com.example.trello.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001, "user existed"),
    USERNAME_INVALID(1002, "NAME  ERROR");
    
    private  Integer  code;
    private String message;
}
