package com.example.trello.Dto.Request;

import java.util.List;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequest {

    @Getter
    public static class createCard {
        UUID boardId;
        UUID columnId;
        String title;
        String status;
        List<UUID> commentIds;
        String attached;
        List<UUID> memberIds;

    }
}
