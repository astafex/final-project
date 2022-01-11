package com.github.astafex.finalproject.service;

import com.github.astafex.finalproject.atm.entity.Card;
import com.github.astafex.finalproject.server.entity.Account;
import com.github.astafex.finalproject.server.entity.Balance;
import com.github.astafex.finalproject.server.entity.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerTest {
    Account account;
    Card card;

    @BeforeEach
    void setUp() {
        account = new Account(
                new HashSet<>(),
                "40817810570000123456",
                new Balance(BigDecimal.TEN, Currency.USD)
        );
        card = new Card(
                "1234 5678 8765 4321",
                "Artem Astafev",
                LocalDate.of(2030, 1, 1),
                "0000"
        );
    }

    @Test
    void getAccountFromCardTest() {
        assertEquals(account, Server.getAccountFromCard(card));
    }
}