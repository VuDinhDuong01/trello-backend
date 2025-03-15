package com.example.trello.Service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.trello.Dto.Request.UserRequest;
import com.example.trello.Dto.Response.UserResponse;
import com.example.trello.Entity.TokenEntity;
import com.example.trello.Entity.UserEntity;
import com.example.trello.Exception.ForbiddenErrorException;
import com.example.trello.Mapper.UserMapper;
import com.example.trello.Repository.TokenRepository;
import com.example.trello.Repository.UserRepository;
import com.example.trello.Service.EmailService;
import com.example.trello.Service.JwtService;
import com.example.trello.Service.UserService;
import com.example.trello.Util.Util;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Value("${spring.jwt.secretKey_access_token}")
    String secret_access_key;

    @Value("${spring.jwt.secretKey_refresh_token}")
    String secret_refresh_key;

    @Autowired
    UserRepository userRepository;
    // @Autowired
    // RedisService redisService;
    @Autowired
    EmailService emailService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public UserResponse.VerifyEmail VerifyEmail(UserRequest.VerifyEmail payload) {
        UserEntity user = userRepository.findByEmail(payload.getEmail());
        if (user != null) {
            throw new ForbiddenErrorException("user not existed");
        }
        String subject = "";
        String token = Util.randomToken();
        // redisService.saveValueToRedis(payload.getEmail() + "_register", token);
        emailService.sendNewMail(payload.getEmail(), subject, token);
        // Object tokenRedis = redisService.getValueFromRedis(payload.getEmail() +
        // "_register");

        UserResponse.VerifyEmail verifyEmail = new UserResponse.VerifyEmail();
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(payload.getEmail());
        userRepository.save(userEntity);

        verifyEmail.setEmail(payload.getEmail());
        return verifyEmail;
    }

    @Override
    public UserResponse.VerifyToken VerifyToken(UserRequest.VerifyToken payload) {

        UserEntity user = userRepository.findByEmail(payload.getEmail());
        if (user == null) {
            throw new ForbiddenErrorException("user not existed");
        }

        // Object token = redisService.getValueFromRedis(payload.getEmail() +
        // "_register");

        // if (token == null) {
        // throw new ForbiddenErrorException("Token đã hết hạn");
        // }

        // if (!token.equals(payload.getToken())) {
        // throw new ForbiddenErrorException("Token bạn nhập chưa đúng");
        // }

        // redisService.deleteValue(payload.getEmail() + "_register");
        UserResponse.VerifyToken verifyToken = new UserResponse.VerifyToken();
        verifyToken.setEmail(payload.getEmail());

        return verifyToken;
    }

    public String SendToken(UserRequest.SendToken payload) {
        UserEntity user = userRepository.findByEmail(payload.getEmail());
        if (user == null) {
            throw new ForbiddenErrorException("Bạn chưa verify tài khoản");
        }

        String subject = "";
        String token = Util.randomToken();
        // redisService.saveValueToRedis(payload.getEmail() + "_register", token);
        // Object tokenRedis = redisService.getValueFromRedis(payload.getEmail() +
        // "_register");
        // System.out.println("tokenRedis:" + tokenRedis);
        emailService.sendNewMail(payload.getEmail(), subject, token);

        return "Token đã được giửi về gmail của bạn.";
    }

    public UserResponse.Register register(UserRequest.Register payload) {
        UserEntity user = userRepository.findByEmail(payload.getEmail());
        if (user == null) {
            throw new ForbiddenErrorException("user not exist");
        }
        UserEntity userMapperRequest = userMapper.toUser(payload);
        // user.setEmail(payload.getEmail());
        userMapperRequest.setCreatedAt(new Date());
        userMapperRequest.setIsActive(true);
        userMapperRequest.setCreatedBy(null);
        userMapperRequest.setIsActive(true);
        userMapperRequest.setPassword(passwordEncoder.encode(payload.getPassword()));
        // user.setUsername(payload.getUsername());
        userRepository.save(userMapperRequest);

        UserResponse.Register register = new UserResponse.Register();
        register.setMessage("register success");
        return register;
    }

    public Map<String, Object> login(UserRequest.Login payload) {

        UserEntity user = userRepository.findByEmail(payload.getEmail());
        if (user == null) {
            throw new ForbiddenErrorException("user not exist");
        }

        if (Boolean.TRUE.equals(user.getIsDelete()) || Boolean.FALSE.equals(user.getIsActive())) {
            throw new ForbiddenErrorException("Tài khoản của bạn chưa được kích hoạt hoặc đã bị khóa.");
        }

        Boolean decoderPassword = passwordEncoder.matches(payload.getPassword(), user.getPassword());

        if (!decoderPassword) {
            throw new ForbiddenErrorException("password not correct");
        }

        String accessToken = jwtService.generateToken(user.getId().toString(),
                secret_access_key, 1000 * 60 * 60);
        String refreshToken = jwtService.generateToken(user.getId().toString(),
                secret_refresh_key, 1000 * 60 * 60);

        Map<String, Object> response = new HashMap<>();

        UserResponse.Token token = new UserResponse.Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);

        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(refreshToken);
        tokenEntity.setCreatedAt(new Date());
        tokenEntity.setCreatedBy(user.getId());
        tokenRepository.save(tokenEntity);

        response.put("record", user);
        response.put("token", token);

        return response;
    }

    public UserEntity getMe() {
        String userId = Util.getIdByToken();
        UserEntity user = userRepository.findById(UUID.fromString(userId)).orElse(null);
        return user;
    }

    public UserEntity updateMe(UserRequest.UpdateMe payload) {
        String userId = Util.getIdByToken();
        UserEntity user = userRepository.findById(UUID.fromString(userId)).orElse(null);

        user.setAvatar(payload.getAvatar());
        user.setUsername(payload.getUsername());
        user.setUpdatedAt(new Date());
        user.setUpdatedBy(UUID.fromString(userId));

        return user;
    }

    public String logout(UserRequest.Logout payload) {
        TokenEntity findToken = tokenRepository.findByToken(payload.getRefresh_token());
        System.out.println("token" + findToken);
        if (findToken == null) {
            throw new ForbiddenErrorException("token not exist");
        }
        tokenRepository.deleteByToken(payload.getRefresh_token());

        return "logout success";
    }

    public Map<String, String> refreshToken(UserRequest.Logout payload) {
        TokenEntity findToken = tokenRepository.findByToken(payload.getRefresh_token());

        if (findToken == null) {
            throw new ForbiddenErrorException("token not exist");
        }
        String userId = jwtService.extractUserId(payload.getRefresh_token(),
                secret_refresh_key);
        String accessToken = jwtService.generateToken(userId,
                secret_access_key, 1000 * 60 * 60);

        Map<String, String> mapToken = new HashMap<>();
        mapToken.put("access_token", accessToken);
        mapToken.put("refresh_token", payload.getRefresh_token());
        return mapToken;
    }

}
