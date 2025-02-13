package com.example.trello.Entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "card")
public class CardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "board_id")
    UUID boardId;

    @Column(name = "column_id")
    UUID columnId;

    String title;
    
    @Column(name = "member_ids")
    List<UUID> memberIds;

    String image;

    String attached;

    String status ="ACTIVE";

    @Column(name = "comment_ids")
    List<UUID> commentIds;
}
