package com.example.trello.Dto.Request;

import java.util.List;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ColumnRequest {
    @Getter
    public static class createColumn {
        UUID boardId;
        String title;
        String status;
        List<UUID> cardOrders;
    }

    @Getter 
    public static class deleteColumn{
        String columnId;
    }

    @Getter
    public static class getColumn {
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
}
