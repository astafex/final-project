package com.github.astafex.finalproject.repository;

import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.dto.CardDto;
import com.github.astafex.finalproject.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class CardRepositoryTest {

    @Autowired
    private CardService cardService;
    private BalanceDto balance;
    private CardDto card;

    @BeforeEach
    void init() {
        balance = new BalanceDto(BigDecimal.valueOf(33333.33), "USD");
        card = new CardDto("3333333333333333", 3333);
    }

    @Test
    void getCardByNumber_NotFound() {
    }
}