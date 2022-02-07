package com.github.astafex.finalproject.client;

import com.github.astafex.finalproject.atm.Card;
import com.github.astafex.finalproject.server.Balance;
import com.github.astafex.finalproject.server.Currency;
import com.github.astafex.finalproject.service.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ATMTest {
    Card card;
    Balance balance;
    ATM atm;

    @BeforeEach
    void setUp() {
        card = new Card(
                "1234 5678 8765 4321",
                "Artem Astafev",
                LocalDate.of(2030, 1, 1),
                "0000"
        );

        balance = new Balance(BigDecimal.TEN, Currency.USD);

        atm = new ATM(new Server());
    }

    @Test
    void getAccountBalanceFromCardTest() {
        assertEquals(balance, atm.getCardAccountBalance(card));
    }
}