package com.example.trello.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;

@Service
@Data
// @FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtService {

    public String generateToken(String userId, String secret, Integer expri) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userId, secret, expri);
    }

    // Create a JWT token with specified claims and subject (user name)
    String createToken(Map<String, Object> claims, String userId, String secret , Integer expri) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expri))
                .signWith(getSignKey(secret), io.jsonwebtoken.SignatureAlgorithm.HS256)
                .compact();
    }

    // Get the signing key for JWT token
    Key getSignKey(String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    String extractUserId(String token, String secret) {
        return extractClaim(token, secret, Claims::getSubject);
    }

    <T> T extractClaim(String token, String secret, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token, secret);
        return claimsResolver.apply(claims);
    }

    // // Extract all claims from the token
    Claims extractAllClaims(String token, String secret) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey(
                        secret))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
