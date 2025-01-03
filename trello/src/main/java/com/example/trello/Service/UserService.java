package com.example.trello.Service;

import java.util.Date;
import org.springframework.stereotype.Service;

import com.example.trello.Dto.Request.UserRequest;
import com.example.trello.Dto.Response.UserResponse;
import com.example.trello.Entity.UserEntity;
import com.example.trello.Exception.ForbiddenErrorException;
import com.example.trello.Repository.UserRepository;
import com.example.trello.Util.Util;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;
    RedisService redisService;
    EmailService emailService;

    public UserResponse.VerifyEmail VerifyEmail(UserRequest.VerifyEmail payload) {
        UserEntity user = userRepository.findByEmail(payload.getEmail());
        if (user == null) {
            throw new ForbiddenErrorException("user not exist");
        }
        String subject = "";
        String token = Util.randomToken();
        redisService.saveValueToRedis(payload.getEmail() + "_register", token);
        // emailService.sendNewMail(payload.getEmail(), subject, token);

        UserResponse.VerifyEmail verifyEmail = new UserResponse.VerifyEmail();
        verifyEmail.setEmail(user.getEmail());
        return verifyEmail;
    }

    public UserResponse.VerifyToken VerifyToken(UserRequest.VerifyToken payload) {
        UserEntity user = userRepository.findByEmail(payload.getEmail());
        if (user == null) {
            throw new ForbiddenErrorException("user not exist");
        }

        Object token = redisService.getValueFromRedis(payload.getEmail() + "_register");
        if (token == null) {
            throw new ForbiddenErrorException("Token đã hết hạn");
        }

        if (token != payload.getToken()) {
            throw new ForbiddenErrorException("Token bạn nhập chưa đúng");
        }

        redisService.deleteValue(payload.getEmail() + "_register");

        UserResponse.VerifyToken verifyToken = new UserResponse.VerifyToken();
        verifyToken.setEmail(payload.getEmail());

        return verifyToken;
    }

    public UserResponse.Register register(UserRequest.Register payload) {
        UserEntity user = userRepository.findByEmail(payload.getEmail());
        if (user == null) {
            throw new ForbiddenErrorException("user not exist");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(payload.getEmail());
        userEntity.setCreatedAt(new Date());
        userEntity.setCreatedBy(null);
        userEntity.setIsActive(true);

        userRepository.save(userEntity);

        UserResponse.Register register = new UserResponse.Register();
        register.setMessage("register success");
        return register;
    }


    public  UserEntity login(UserRequest.Login payload){

        UserEntity user = userRepository.findByEmail(payload.getEmail());
        if (user == null) {
            throw new ForbiddenErrorException("user not exist");
        }

        if(user.getPassword() != payload.getPassword()){
            throw new ForbiddenErrorException("password not correct");
        }

        return user;
    }
}
