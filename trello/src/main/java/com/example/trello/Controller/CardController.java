package com.example.trello.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trello.Dto.Request.CardRequest;
import com.example.trello.Dto.Response.BaseResponse;
import com.example.trello.Dto.Validator.ModuleDescriptionApi;
import com.example.trello.Entity.CardEntity;
import com.example.trello.Service.CardService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Controller
@RestController
@RequestMapping("/api/v1/")
// @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
// @RequiredArgsConstructor
public class CardController {
    
   private final  CardService cardService;

   public CardController(CardService cardService){
    this.cardService = cardService;
   }

    @ModuleDescriptionApi(module = "card", method = "POST", description = "Tạo hàng", name = "", path = "/api/v1/card", matadataAdmin = false, type = "PRIVATE")
    @PostMapping("card")
    public BaseResponse<CardEntity> createBoard(@RequestBody @Valid CardRequest.createCard body) {
        CardEntity response = cardService.createCard(body);
        BaseResponse<CardEntity> result = BaseResponse.<CardEntity>builder().data(response)
                .build();
        return result;
    }
}
