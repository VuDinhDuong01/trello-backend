package com.example.trello.Entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "board")
@Data
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String description;

    String title;

    List<UUID> ownerIds;

    List<UUID> memberIds;

    List<UUID> columnOrders;

    String status = "ACTIVE";

    String avatar;

    String type = "PUBLIC";


}
