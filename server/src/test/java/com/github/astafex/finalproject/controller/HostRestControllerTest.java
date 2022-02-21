package com.github.astafex.finalproject.controller;

import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.dto.CardDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HostRestControllerTest {

    @Autowired
    private TestRestTemplate template;
    private String user;
    private String password;
    private CardDto card;
    private BalanceDto balance;


    @BeforeEach
    public void init() {
        card = new CardDto("1111111111111111", 1111);
        user = "atm";
        password = "password";
        balance = new BalanceDto(new BigDecimal("11111.11"), "USD");
    }

    @Test
    public void getStatusHostTestForAuthorized() {
        ResponseEntity<String> result = template
                .withBasicAuth(user, password)
                .getForEntity("/host/status", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getStatusHostTestForUnauthorized() {
        password = "123";
        ResponseEntity<String> result = template
                .withBasicAuth(user, password)
                .getForEntity("/host/status", String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    @Test
    public void getBalanceByCardTest() {
        HttpEntity<CardDto> httpEntity = new HttpEntity<>(card);
        ResponseEntity<BalanceDto> result = template
                .withBasicAuth(user, password)
                .exchange("/host/card/balance", HttpMethod.POST, httpEntity, BalanceDto.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(balance, result.getBody());
    }
}