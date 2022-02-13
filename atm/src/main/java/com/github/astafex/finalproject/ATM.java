package com.github.astafex.finalproject;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ATM {
    private Server server;

    public Balance getCardBalance(Card card) {
        return server.getCardAccountBalance(card);
    }
}
