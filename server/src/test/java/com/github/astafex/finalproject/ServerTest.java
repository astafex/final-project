package com.github.astafex.finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerTest {
    Card card;
    Balance balance;
    Server server;

    @BeforeEach
    void setUp() {
        card = new Card(
                "1234 5678 8765 4321",
                "Artem Astafev",
                LocalDate.of(2030, 1, 1),
                "0000"
        );

        balance = new Balance(BigDecimal.TEN, Currency.USD);

        server = new Server();
    }

    @Test
    void getCardAccountBalanceTest() {
        assertEquals(balance, server.getCardAccountBalance(card));
    }
}