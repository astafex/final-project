package com.github.astafex.finalproject.service;

import com.github.astafex.finalproject.atm.Card;
import com.github.astafex.finalproject.server.Balance;
import com.github.astafex.finalproject.server.Currency;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Server {
    public Balance getCardAccountBalance(Card card) {
        return new Balance(BigDecimal.TEN, Currency.USD);
    }
}
