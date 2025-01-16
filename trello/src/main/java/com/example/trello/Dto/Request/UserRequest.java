package com.example.trello.Dto.Request;

import lombok.Data;
import lombok.Getter;

@Data
public class UserRequest {

    @Getter
    public static class VerifyEmail{
        String email;
    }

    @Getter
    public static class VerifyToken{
        String email;
        String token;
    }

    @Getter
    public static class Register{
        String email;
        String password;
        String username;
    }

    @Getter
    public static class Login{
        String email;
        String password;
    }

    @Getter
    public static class SendToken{
        String email;
    }

    @Getter
    public static class UpdateMe{
        String avatar;
        String username;
    }

    @Getter
    public static class Logout{
        String refresh_token;
    }

    
}
