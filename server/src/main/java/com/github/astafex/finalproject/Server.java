package com.github.astafex.finalproject;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Server {
    public Balance getCardAccountBalance(Card card) {
        return new Balance(BigDecimal.TEN, Currency.USD);
    }
}
