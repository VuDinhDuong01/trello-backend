package com.example.trello.Dto.Request;

import java.util.List;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequest {
    @Getter
    public static class createComment {
        UUID userId;
        String content;
        List<String> images;
        String attached;
        UUID cardId;
        UUID columnId;
        String status ;
    }
}
