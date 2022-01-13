package com.github.astafex.finalproject.service;

import com.github.astafex.finalproject.atm.entity.Card;
import com.github.astafex.finalproject.server.entity.Balance;
import com.github.astafex.finalproject.server.entity.Currency;

import java.math.BigDecimal;

public class Server {
    public Balance getCardAccountBalance(Card card) {
        return new Balance(BigDecimal.TEN, Currency.USD);
    }
}
