package com.example.trello.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trello.Entity.TokenEntity;


@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, UUID> {
    TokenEntity findByToken(String token);
    void deleteByToken(String token);
}
