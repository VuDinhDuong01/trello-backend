package com.example.trello.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.example.trello.Entity.TokenEntity;

import jakarta.transaction.Transactional;


@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, UUID> {
    TokenEntity findByToken(String token);
     @Modifying
    @Transactional
    void deleteByToken(String token);
}
