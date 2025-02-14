package com.example.trello.Service;

import com.example.trello.Dto.Request.CommentRequest;
import com.example.trello.Entity.CommentEntity;
public interface CommentService {
   CommentEntity createComment(CommentRequest.createComment payload);
}
