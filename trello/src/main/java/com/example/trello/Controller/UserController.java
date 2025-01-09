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
import com.example.trello.Exception.ServerErrorException;
import com.example.trello.Service.UserService;
import com.example.trello.Util.CustomValidation.ModuleDescriptionApi;

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

    @ModuleDescriptionApi(module = "user", method = "POST", description = "Xác thực email", name = "Xác thực email của user trước khi đăng ký tài khoản", path = "/api/v1/verify-email", matadataAdmin = false, type = "PUBLIC")
    @PostMapping("/verify-email")
    public BaseResponse<UserResponse.VerifyEmail> VerifyEmail(@RequestBody @Valid UserRequest.VerifyEmail body) {
        // BaseResponse<UserResponse.VerifyEmail> result = null;
        // try {
        UserResponse.VerifyEmail response = userService.VerifyEmail(body);
        BaseResponse<UserResponse.VerifyEmail> result = BaseResponse.<UserResponse.VerifyEmail>builder().data(response)
                .build();
        // } catch (Exception e) {
        // throw new ServerErrorException("Server error");
        // }
        return result;
    }

    @ModuleDescriptionApi(module = "user", description = "Xác thực token", method = "POST", name = "Xác thực mã giửi vể email", path = "/api/v1/verify-token", matadataAdmin = false, type = "PUBLIC")
    @PostMapping("/verify-token")
    public BaseResponse<UserResponse.VerifyToken> VerifyToken(@RequestBody @Valid UserRequest.VerifyToken body) {
        // BaseResponse<UserResponse.VerifyToken> result = null;

        // try {
        UserResponse.VerifyToken response = userService.VerifyToken(body);
        BaseResponse<UserResponse.VerifyToken> result = BaseResponse.<UserResponse.VerifyToken>builder().data(response)
                .build();
        // } catch (Exception e) {
        // throw new ServerErrorException("Server error");
        // }
        return result;
    }

    @ModuleDescriptionApi(module = "user", method = "POST", description = "Gửi lại mã token", name = "", path = "/api/v1/send-token", matadataAdmin = false, type = "PUBLIC")
    @PostMapping("/send-token")
    public BaseResponse<String> SendToken(@RequestBody @Valid UserRequest.SendToken body) {
        String response = userService.SendToken(body);
        BaseResponse<String> result = BaseResponse.<String>builder().data(response).build();
        return result;
    }

    @ModuleDescriptionApi(module = "user", method = "POST", description = "Đăng ký", name = "Đăng ký tài khoản", path = "/api/v1/register", matadataAdmin = false, type = "PUBLIC")
    @PostMapping("/register")
    public BaseResponse<UserResponse.Register> register(@RequestBody @Valid UserRequest.Register body) {

        UserResponse.Register response = userService.register(body);
        BaseResponse<UserResponse.Register> result = BaseResponse.<UserResponse.Register>builder().data(response)
                .build();
        return result;
    }

    @ModuleDescriptionApi(module = "user", description = "Đăng nhập", method = "POST", name = "Đăng ký tài khoản", path = "/api/v1/login", matadataAdmin = false, type = "PUBLIC")
    @PostMapping("/login")
    public BaseResponse<Map<String, Object>> login(@RequestBody @Valid UserRequest.Login body) {
        Map<String, Object> response = userService.login(body);
        BaseResponse<Map<String, Object>> result = BaseResponse.<Map<String, Object>>builder().data(response).build();
        return result;
    }

}
