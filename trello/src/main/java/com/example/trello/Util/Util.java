package com.example.trello.Util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class Util {
    public static String randomToken() {
        String random = "";
        for (int i = 0; i < 6; i++) {
            random = random + (int)(Math.floor(Math.random()*10));
        }
        return random;
    }
    public  static String getIdByToken(){
        Authentication authentication =(Authentication) SecurityContextHolder.getContext().getAuthentication();
       Jwt jwt = (Jwt) authentication.getPrincipal();
       String email = jwt.getSubject();

       return email != null ? email : "";
    }
}
