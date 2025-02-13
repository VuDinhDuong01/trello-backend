package com.example.trello.Entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "column-trello")
@Data
public class ColumnEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "board_id")
    UUID boardId;

    String title;

    @Column(name = "card_orders")
    List<UUID> cardOrders;

    String status ="ACTIVE";
}
