package com.example.trello.Service;

import java.security.Key;
import java.util.Map;
import java.util.function.Function;
import io.jsonwebtoken.Claims;

public interface JwtService {
    abstract String generateToken(String userId, String secret, Integer expri);

    abstract String createToken(Map<String, Object> claims, String userId, String secret, Integer expri);

    abstract Key getSignKey(String secret);

    abstract String extractUserId(String token, String secret);

    abstract <T> T extractClaim(String token, String secret, Function<Claims, T> claimsResolver);

    abstract Claims extractAllClaims(String token, String secret);
}
