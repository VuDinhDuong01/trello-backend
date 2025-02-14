package com.example.trello.Service;
public interface EmailService {
    abstract void sendNewMail(String to, String subject, String body);
}
