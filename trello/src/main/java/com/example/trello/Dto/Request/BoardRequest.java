package com.example.trello.Dto.Request;

import java.util.List;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BoardRequest {

    @Getter
    public static class createBoard {
        String title;
        List<UUID> ownerIds;
        List<UUID> memberIds;
        String permissionViewer;
        String background;
        List<UUID> columnOrders;
        String status;
    }

    @Getter
    public static class deleteBoard {
        String id;
    }

    @Getter
    public static class getBoard {
        String sortField;
        String sortType;

        Integer limit;
        Integer start;

        List<condition> conditions;
    }

    @Getter
    public static class condition {
        String key;
        List<Object> value;
    }
    @Getter 
    public static class getDetail{
        String id;
    }
}
