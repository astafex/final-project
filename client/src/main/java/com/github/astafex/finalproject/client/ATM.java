package com.github.astafex.finalproject.client;

import com.github.astafex.finalproject.atm.Card;
import com.github.astafex.finalproject.server.Balance;
import com.github.astafex.finalproject.service.Server;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ATM {
    private Server server;

    public Balance getCardAccountBalance(Card card) {
        return server.getCardAccountBalance(card);
    }
}
