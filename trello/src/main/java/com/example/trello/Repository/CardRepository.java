package com.example.trello.Repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trello.Entity.CardEntity;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {
    Page<CardEntity> findAll(Specification<CardEntity> spec, Pageable pageable);

}