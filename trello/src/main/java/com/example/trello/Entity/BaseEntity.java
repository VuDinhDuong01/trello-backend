package com.example.trello.Entity;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity {

    @Column(name = "is_delete")
    @JsonProperty("isDelete")
    Boolean isDelete = false;

    @Column(name = "created_at")
    @CreationTimestamp
    @JsonProperty("createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonProperty("updatedAt")
    // chỉ đinh lưu xuống db kiểu timeTamp
    @Temporal(TemporalType.TIMESTAMP)
    Date updatedAt;

    @Column(name = "created_by")
    @JsonProperty("createdBy")
    UUID createdBy;

    @Column(name = "updated_by")
    @JsonProperty("updatedBy")
    UUID updatedBy;

}
