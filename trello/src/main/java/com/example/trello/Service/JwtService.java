package com.example.trello.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Service
@Data
// @FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtService {

    public String generateToken(String userId, String secret) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userId, secret);
    }

    // Create a JWT token with specified claims and subject (user name)
     String createToken(Map<String, Object> claims, String userId, String secret) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(secret), io.jsonwebtoken.SignatureAlgorithm.HS256)
                .compact();
    }

    // Get the signing key for JWT token
     Key getSignKey(String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

   //   String extractUsername(String token) {
   //      return extractClaim(token, Claims::getSubject);
   //  }

    // Extract the expiration date from the token
   //   Date extractExpiration(String token) {
   //      return extractClaim(token, Claims::getExpiration);
   //  }

    // Extract a claim from the token
   //   <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
   //      final Claims claims = extractAllClaims(token);
   //      return claimsResolver.apply(claims);
   //  }

   //  // Extract all claims from the token
   //   Claims extractAllClaims(String token) {
   //      return Jwts.parserBuilder()
   //              .setSigningKey(getSignKey())
   //              .build()
   //              .parseClaimsJws(token)
   //              .getBody();
   //  }

   //   Boolean isTokenExpired(String token) {
   //      return extractExpiration(token).before(new Date());
   //  }

   //  // Validate the token against user details and expiration
   //   Boolean validateToken(String token, UserDetails userDetails) {
   //      final String username = extractUsername(token);
   //      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
   //  }
}
