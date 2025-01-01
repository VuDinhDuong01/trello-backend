package com.example.trello.Util;

public class Util {
    public static String randomToken() {
        String random = "";
        for (int i = 0; i < 6; i++) {
            random = random + Math.floor(Math.random() * 10);
        }
        return random;
    }
}
