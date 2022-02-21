package com.github.astafex.finalproject.service;

import com.github.astafex.finalproject.dto.AccountDto;
import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.dto.CardDto;
import com.github.astafex.finalproject.entity.Account;
import com.github.astafex.finalproject.entity.Balance;
import com.github.astafex.finalproject.entity.Card;
import com.github.astafex.finalproject.exception.CardCheckException;
import com.github.astafex.finalproject.exception.CardNotFoundException;
import com.github.astafex.finalproject.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CardService {
    private CardRepository cardRepository;

    public AccountDto getAccount(CardDto cardDto) {
        Card card = cardRepository.getCardByNumber(cardDto.getNumber())
                .orElseThrow(CardNotFoundException::new);

        if (card.getPIN() != cardDto.getPIN()) {
            throw new CardCheckException("Введен неверный PIN.");
        }
        if (card.getExpirationDate().isBefore(LocalDate.now())) {
            throw new CardCheckException("Срок действия карты истек.");
        }
        if (card.isBlocked()) {
            throw new CardCheckException("Карта заблокирована.");
        }

        Account account = card.getAccount();
        Balance balance = account.getBalance();

        BalanceDto balanceDto = new BalanceDto(balance.getAmount(), balance.getCurrency().name());

        return new AccountDto(account.getNumber(), balanceDto);
    }
}