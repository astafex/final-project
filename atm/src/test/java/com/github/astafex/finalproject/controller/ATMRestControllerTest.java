package com.github.astafex.finalproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.service.CardOperation;
import com.github.astafex.finalproject.service.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc()
class ATMRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ATMRestController controller;

    @Mock
    private CardOperation operation;

    @BeforeEach
    void setUp() {
        when(operation.getBalance("1111111111111111", 1111))
                .thenReturn(new BalanceDto(BigDecimal.valueOf(11111.11), "RUB"));
    }

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void getBalanceByCard_successTest() {
        controller = new ATMRestController(operation);
        Response response = controller.getBalanceByCard("1111111111111111", 1111);
        assertThat(response).isEqualTo(new Response(200, HttpStatus.OK.name(), "Баланс карты: 11111.11 RUB"));
    }

    @Test
    void getBalanceByCard_internalServerErrorTest() throws Exception {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.mockMvc
                .perform(get("/ATM/balance/card?number=1&pin=1"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(
                        new Response(status.value(), status.name(), "Сервер не может обработать запрос"))));
    }
}
