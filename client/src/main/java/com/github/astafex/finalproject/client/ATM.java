package com.github.astafex.finalproject.client;

import com.github.astafex.finalproject.atm.entity.Card;
import com.github.astafex.finalproject.server.entity.Balance;
import com.github.astafex.finalproject.service.Server;

public class ATM {
    public Balance getCardAccountBalance(Card card) {
        return new Server().getCardAccountBalance(card);
    }
}
