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
@Table(name = "comment")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "user_id")
    UUID userId;

    String content;

    List<String> images;

    String attached;

    @Column(name = "card_id")
    UUID cardId;

    @Column(name = "columnId")
    UUID columnId;

    String status = "ACTIVE";

}
