package com.github.astafex.finalproject.service;

import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.entity.Account;
import com.github.astafex.finalproject.entity.Balance;
import com.github.astafex.finalproject.entity.Card;
import com.github.astafex.finalproject.exception.CardCheckException;
import com.github.astafex.finalproject.exception.CardNotFoundException;
import com.github.astafex.finalproject.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CardService {
    private static final Logger LOG = LoggerFactory.getLogger(CardService.class);
    private final CardRepository cardRepository;

    public BalanceDto getBalance(String number, int PIN) {
        Card card = cardRepository.getCardByNumber(number)
                .orElseThrow(CardNotFoundException::new);
        checkCard(card, PIN);

        Account account = card.getAccount();
        Balance balance = account.getBalance();

        return new BalanceDto(balance.getAmount(), balance.getCurrency().name());
    }

    private void checkCard(Card card, int PIN) {
        String checkMessage = "";

        if (card.getPIN() != PIN) {
            checkMessage = "Введен неверный PIN";
        } else if (card.isBlocked()) {
            checkMessage = "Карта заблокирована";
        } else if (card.getExpirationDate().isBefore(LocalDate.now())) {
            checkMessage = "Срок действия карты истек";
        }
        if (!checkMessage.isEmpty()) {
            try {
                throw new CardCheckException(checkMessage);
            } catch (CardCheckException ex) {
                LOG.error("Exception thrown: ", ex.fillInStackTrace());
                throw ex;
            }
        }
    }
}