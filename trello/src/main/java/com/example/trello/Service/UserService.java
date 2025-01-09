package com.example.trello.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
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
    // @Value("${spring.jwt.secretKey_access_token}")
    // String secret_access_key;

    // @Value("${spring.jwt.secretKey_refresh_token}")
    // String secret_refresh_key;
    // @Value("${spring.mail.host}")
    // String host;

    UserRepository userRepository;
    RedisService redisService;
    EmailService emailService;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;

    public UserResponse.VerifyEmail VerifyEmail(UserRequest.VerifyEmail payload) {
        UserEntity user = userRepository.findByEmail(payload.getEmail());
        if (user != null) {
            throw new ForbiddenErrorException("user not existed");
        }
        String subject = "";
        String token = Util.randomToken();
        redisService.saveValueToRedis(payload.getEmail() + "_register", token);
        Object tokenRedis = redisService.getValueFromRedis(payload.getEmail() + "_register");
        System.out.println("tokenRedis:" + tokenRedis);
        // emailService.sendNewMail(payload.getEmail(), subject, token);

        UserResponse.VerifyEmail verifyEmail = new UserResponse.VerifyEmail();
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(payload.getEmail());
        userRepository.save(userEntity);

        verifyEmail.setEmail(payload.getEmail());
        return verifyEmail;
    }

    public UserResponse.VerifyToken VerifyToken(UserRequest.VerifyToken payload) {

        UserEntity user = userRepository.findByEmail(payload.getEmail());
        if (user == null) {
            throw new ForbiddenErrorException("user not existed");
        }

        Object token = redisService.getValueFromRedis(payload.getEmail() + "_register");
        System.out.println("token:" + token);

        if (token == null) {
            throw new ForbiddenErrorException("Token đã hết hạn");
        }

        if (!token.equals(payload.getToken())) {
            throw new ForbiddenErrorException("Token bạn nhập chưa đúng");
        }

        redisService.deleteValue(payload.getEmail() + "_register");
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
        redisService.saveValueToRedis(payload.getEmail() + "_register", token);
        Object tokenRedis = redisService.getValueFromRedis(payload.getEmail() + "_register");
        System.out.println("tokenRedis:" + tokenRedis);
        // emailService.sendNewMail(payload.getEmail(), subject, token);

        return "Token đã được giửi về gmail của bạn.";
    }

    public UserResponse.Register register(UserRequest.Register payload) {
        UserEntity user = userRepository.findByEmail(payload.getEmail());
        if (user == null) {
            throw new ForbiddenErrorException("user not exist");
        }
        user.setEmail(payload.getEmail());
        user.setCreatedAt(new Date());
        user.setIsActive(true);
        user.setCreatedBy(null);
        user.setIsActive(true);
        user.setPassword(passwordEncoder.encode(payload.getPassword()));

        userRepository.save(user);

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
                "Gd7Jf9vZsPiZhvQ5l3X8mYvR6P8jTv1L2xQ6jYuTzWvR5dMfH2k7gQ==");
        String refreshToken = jwtService.generateToken(user.getId().toString(),
                "Gd8Jf9vZsPiZhvQ5l3X8mYvR6P8jTv1L2xQ6jYuTzWvR5dMfH2k7gQ==");

        Map<String, Object> response = new HashMap<>();

        UserResponse.Token token = new UserResponse.Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);

        response.put("record", user);
        response.put("token", token);

        return response;
    }
}
