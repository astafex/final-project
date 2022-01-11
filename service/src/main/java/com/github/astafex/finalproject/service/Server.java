package com.github.astafex.finalproject.service;

import com.github.astafex.finalproject.atm.entity.Card;
import com.github.astafex.finalproject.server.entity.Account;
import com.github.astafex.finalproject.server.entity.Balance;
import com.github.astafex.finalproject.server.entity.Currency;

import java.math.BigDecimal;
import java.util.HashSet;

public class Server {
    public static Account getAccountFromCard(Card card) {
        return new Account(
                new HashSet<>(),
                "40817810570000123456",
                new Balance(BigDecimal.TEN, Currency.USD)
        );
    }
}
