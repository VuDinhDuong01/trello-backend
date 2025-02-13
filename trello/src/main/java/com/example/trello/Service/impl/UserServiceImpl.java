package com.example.trello.Service.impl;

import java.util.Map;

import com.example.trello.Dto.Request.UserRequest;
import com.example.trello.Dto.Response.UserResponse;
import com.example.trello.Entity.UserEntity;

public interface UserServiceImpl {

    abstract UserResponse.VerifyEmail VerifyEmail(UserRequest.VerifyEmail request);

    abstract UserResponse.VerifyToken VerifyToken(UserRequest.VerifyToken request);

    abstract String SendToken(UserRequest.SendToken request);

    abstract UserResponse.Register register(UserRequest.Register request);

    abstract Map<String, Object> login(UserRequest.Login request);

    abstract UserEntity getMe();

    abstract UserEntity updateMe(UserRequest.UpdateMe request);

    abstract String logout(UserRequest.Logout request);

    abstract Map<String, String> refreshToken(UserRequest.Logout request);
}
