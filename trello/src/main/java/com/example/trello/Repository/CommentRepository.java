package com.example.trello.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trello.Entity.CommentEntity;

public interface CommentRepository  extends JpaRepository<CommentEntity, UUID>{
    
}
