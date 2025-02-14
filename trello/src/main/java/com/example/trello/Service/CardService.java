package com.example.trello.Service;

import com.example.trello.Dto.Request.CardRequest;
import com.example.trello.Entity.CardEntity;
public interface  CardService {

  abstract CardEntity createCard(CardRequest.createCard payload);

}
