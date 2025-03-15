package com.example.trello.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001, "user existed"),
    USERNAME_INVALID(1002, "NAME  ERROR"),
    INVALID_KEY(1003,"INVALID_KEY");
    
    private  Integer  code;
    private String message;
}
