package com.example.trello.Repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trello.Entity.ColumnEntity;

@Repository
public interface ColumnRepository extends JpaRepository<ColumnEntity, UUID>{
    Page<ColumnEntity> findAll(Specification<ColumnEntity> spec, Pageable pageable);
} 