package com.example.trello.Dto.Response;

import com.example.trello.Entity.UserEntity;

import lombok.Data;

@Data
public class UserResponse {

    @Data
    public static class VerifyEmail{
        String email;
    }

    @Data
    public static class VerifyToken{
        String email;
    }

    @Data
    public static class Register{
        String message;
    }

    @Data
    public static class Login{
        UserEntity user;
    }
}
