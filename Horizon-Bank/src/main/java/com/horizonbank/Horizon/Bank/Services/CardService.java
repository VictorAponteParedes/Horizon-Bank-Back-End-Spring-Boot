package com.horizonbank.Horizon.Bank.Services;

import com.horizonbank.Horizon.Bank.Entities.Card;
import com.horizonbank.Horizon.Bank.Entities.User;
import com.horizonbank.Horizon.Bank.Repositories.CardRepository;
import com.horizonbank.Horizon.Bank.Repositories.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;

    public Card createCard(Card card, Long userId) {
        User user = null;
        try {
            user = userRepository.findById(userId).get();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Usuario no encontrado", e);
        }
        card.setUser(user);
        card.setCardHolderName(user.getName());
        return cardRepository.save(card);
    }

    public Card getCard(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de tarjeta inválido");
        }

        return cardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarjeta no encontrada"));
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }

}
