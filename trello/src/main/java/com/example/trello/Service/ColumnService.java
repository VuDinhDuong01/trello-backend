package com.example.trello.Service;

import java.util.Map;

import com.example.trello.Dto.Request.ColumnRequest;
import com.example.trello.Entity.ColumnEntity;
public interface ColumnService {
    abstract ColumnEntity createColumn(ColumnRequest.createColumn payload);

    abstract String deleteColumn(ColumnRequest.deleteColumn payload);

    abstract Map<String, Object> getColumn(ColumnRequest.getColumn payload);

}
