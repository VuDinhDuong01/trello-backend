package com.example.trello.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.trello.Dto.Request.CommentRequest;
import com.example.trello.Entity.CardEntity;
import com.example.trello.Entity.ColumnEntity;
import com.example.trello.Entity.CommentEntity;
import com.example.trello.Repository.CardRepository;
import com.example.trello.Repository.CommentRepository;
import com.example.trello.Util.Util;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CommentService {
    CommentRepository commentRepository;
    CardRepository cardRepository;

    public CommentEntity createComment(CommentRequest.createComment payload){
        String userId = Util.getIdByToken();
        List<UUID> commentIds = new ArrayList<>();
        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setUserId(UUID.fromString(userId));
        commentEntity.setCardId(payload.getCardId());
        commentEntity.setStatus(payload.getStatus());
        commentEntity.setContent(payload.getContent());
        commentEntity.setCreatedBy(UUID.fromString(userId));
        commentEntity.setImages(payload.getImages());
        commentEntity.setColumnId(payload.getColumnId());
        commentEntity.setAttached(payload.getAttached());

        commentRepository.save(commentEntity);

        UUID commentId = commentEntity.getId();
        CardEntity findCardEntity = cardRepository.findById(payload.getCardId()).orElse(null);
        if (findCardEntity == null) {

        }
     
        commentIds.add(commentId);
        findCardEntity.setCommentIds(commentIds);
        cardRepository.save(findCardEntity);

        return commentEntity;
    }
}
