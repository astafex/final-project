package com.github.astafex.finalproject.client;

import com.github.astafex.finalproject.atm.entity.Card;
import com.github.astafex.finalproject.server.entity.Balance;
import com.github.astafex.finalproject.server.entity.Currency;

import java.math.BigDecimal;

public class ATM {
    public static Balance getAccountBalanceFromCard(Card card) {
        return new Balance(BigDecimal.TEN, Currency.USD);
    }
}
