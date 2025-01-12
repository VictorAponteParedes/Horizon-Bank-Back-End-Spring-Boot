package com.horizonbank.Horizon.Bank.Controllers;

import com.horizonbank.Horizon.Bank.Entities.Card;
import com.horizonbank.Horizon.Bank.Services.CardService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<Card> createCard(
            @RequestParam Long userId,
            @RequestBody Card card) {
        try {

            Card createdCard = cardService.createCard(card, userId);
            return new ResponseEntity<>(createdCard, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCard(@PathVariable Long id) {
        try {
            Card card = cardService.getCard(id);
            return new ResponseEntity<>(card, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardService.getAllCards();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable Long id) {
        try {
            cardService.deleteCard(id);
            return new ResponseEntity<>("Tarjeta eliminada exitosamente", HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Tarjeta no encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
