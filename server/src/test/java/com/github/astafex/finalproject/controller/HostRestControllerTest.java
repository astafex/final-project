package com.github.astafex.finalproject.controller;

import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.dto.CardDto;
import com.github.astafex.finalproject.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class HostRestControllerTest {

    private final CardService cardService = Mockito.mock(CardService.class);
    private final CardDto card = new CardDto("1111111111111111", 1111);
    private final BalanceDto balanceExpect = new BalanceDto(BigDecimal.valueOf(11111.11), "RUB");
    private HostRestController controller;


    @BeforeEach
    void setUp() {
        when(cardService.getBalance(card.getNumber(), card.getPIN()))
                .thenReturn(new BalanceDto(BigDecimal.valueOf(11111.11), "RUB"));
        controller = new HostRestController(cardService);
    }

    @Test
    void getBalanceByCard_test() {
        BalanceDto balanceProvide = controller.getBalanceByCard(card);
        assertThat(balanceExpect).isEqualTo(balanceProvide);
    }
}