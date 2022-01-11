package com.github.astafex.finalproject.client;

import com.github.astafex.finalproject.atm.entity.Card;
import com.github.astafex.finalproject.server.entity.Balance;
import com.github.astafex.finalproject.server.entity.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ATMTest {
    Card card;
    Balance balance;

    @BeforeEach
    void setUp() {
        card = new Card(
                "1234 5678 8765 4321",
                "Artem Astafev",
                LocalDate.of(2030, 1, 1),
                "0000"
        );

        balance = new Balance(BigDecimal.TEN, Currency.USD);
    }

    @Test
    void getAccountBalanceFromCardTest() {
        assertEquals(balance, ATM.getAccountBalanceFromCard(card));
    }
}