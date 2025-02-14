package com.example.trello.Service;

import java.util.Map;

import com.example.trello.Dto.Request.BoardRequest;
import com.example.trello.Entity.BoardEntity;

public interface BoardService {

    abstract BoardEntity createBoard(BoardRequest.createBoard payload);
    abstract String deleteBoard(BoardRequest.deleteBoard payload);
    abstract Map<String, Object> getBoard(BoardRequest.getBoard payload);
   
}
