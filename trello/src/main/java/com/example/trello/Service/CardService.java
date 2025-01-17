package com.example.trello.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.trello.Dto.Request.CardRequest;
import com.example.trello.Entity.CardEntity;
import com.example.trello.Entity.ColumnEntity;
import com.example.trello.Repository.CardRepository;
import com.example.trello.Repository.ColumnRepository;
import com.example.trello.Util.Util;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CardService {

    CardRepository cardRepository;
    ColumnRepository columnRepository;

    public CardEntity createCard(CardRequest.createCard payload) {

        String userId = Util.getIdByToken();
        List<UUID> commentIds = new ArrayList<>();
        List<UUID> memberIds = new ArrayList<>();
        CardEntity cardEntity = new CardEntity();

        cardEntity.setTitle(payload.getTitle());
        cardEntity.setBoardId(payload.getBoardId());
        cardEntity.setStatus(payload.getStatus());
        cardEntity.setCommentIds(commentIds);
        cardEntity.setCreatedBy(UUID.fromString(userId));
        cardEntity.setMemberIds(memberIds);
        cardEntity.setColumnId(payload.getColumnId());
        cardEntity.setAttached(payload.getAttached());

        cardRepository.save(cardEntity);

        UUID cardId = cardEntity.getId();
        ColumnEntity findColumnEntity = columnRepository.findById(payload.getColumnId()).orElse(null);
        if (findColumnEntity == null) {

        }
        List<UUID> orderCards = new ArrayList<>();
        orderCards.add(cardId);
        findColumnEntity.setCardOrders(orderCards);
        columnRepository.save(findColumnEntity);

        return cardEntity;
    }

}
