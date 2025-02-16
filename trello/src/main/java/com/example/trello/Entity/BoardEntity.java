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
@Table(name = "board")
@Data
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String title;

    @Column(name = "owner_ids")
    List<UUID> ownerIds;

    @Column(name = "member_ids")
    List<UUID> memberIds;

    @Column(name = "column_orders")
    List<UUID> columnOrders;

    String status = "ACTIVE";

    String background;
    @Column(name = "permission_viewer")
    String permissionViewer;

}
