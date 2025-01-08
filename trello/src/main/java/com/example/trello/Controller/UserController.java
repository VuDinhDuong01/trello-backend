package com.example.trello.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trello.Dto.Request.UserRequest;
import com.example.trello.Dto.Response.BaseResponse;
import com.example.trello.Dto.Response.UserResponse;
import com.example.trello.Entity.UserEntity;
import com.example.trello.Exception.ServerErrorException;
import com.example.trello.Service.UserService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Controller
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping("/verify-email")
    public BaseResponse<UserResponse.VerifyEmail> VerifyEmail(@RequestBody @Valid UserRequest.VerifyEmail body) {
        // BaseResponse<UserResponse.VerifyEmail> result = null;
        // try {
            UserResponse.VerifyEmail response = userService.VerifyEmail(body);
            BaseResponse<UserResponse.VerifyEmail>  result = BaseResponse.<UserResponse.VerifyEmail>builder().data(response).build();
        // } catch (Exception e) {
        //     throw new ServerErrorException("Server error");
        // }
        return result;
    }

    @PostMapping("/verify-token")
    public BaseResponse<UserResponse.VerifyToken> VerifyToken(@RequestBody @Valid UserRequest.VerifyToken body) {
        // BaseResponse<UserResponse.VerifyToken> result = null;

        // try {
            UserResponse.VerifyToken response = userService.VerifyToken(body);
            BaseResponse<UserResponse.VerifyToken> result = BaseResponse.<UserResponse.VerifyToken>builder().data(response).build();
        // } catch (Exception e) {
        //     throw new ServerErrorException("Server error");
        // }
        return result;
    }

    @PostMapping("/send-token")
    public BaseResponse<String> SendToken(@RequestBody @Valid UserRequest.SendToken body) {
        BaseResponse<String> result = null;

        try {
           String response = userService.SendToken(body);
            result = BaseResponse.<String>builder().data(response).build();
        } catch (Exception e) {
            throw new ServerErrorException("Server error");
        }

        return result;
    }

    @PostMapping("/register")
    public BaseResponse<UserResponse.Register> register(@RequestBody @Valid UserRequest.Register body) {
        BaseResponse<UserResponse.Register> result = null;

        try {
            UserResponse.Register response = userService.register(body);
            result = BaseResponse.<UserResponse.Register>builder().data(response).build();
        } catch (Exception e) {
            throw new ServerErrorException("Server error");
        }

        return result;
    }

    @PostMapping("/login")
    public BaseResponse<Map<String, Object>> login(@RequestBody @Valid UserRequest.Login body) {
            Map<String, Object> response = userService.login(body);
            BaseResponse<Map<String, Object>>  result = BaseResponse.<Map<String, Object>>builder().data(response).build();
        
        return result;
    }
}
