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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorisationTest {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private HostRestController controller;
    private String user;
    private String password;
    private CardDto card;
    private BalanceDto balance;

    @BeforeEach
    void init() {
        card = new CardDto("1111111111111111", 1111);
        user = "atm";
        password = "password";
        balance = new BalanceDto(BigDecimal.valueOf(11111.11), "RUB");
    }

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void getStatusHost_authorizedTest() {
        ResponseEntity<String> result = template
                .withBasicAuth(user, password)
                .getForEntity("/host/status", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getStatusHost_unauthorizedTest() {
        password = "123";
        ResponseEntity<String> result = template
                .withBasicAuth(user, password)
                .getForEntity("/host/status", String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    @Test
    void getBalanceByCard_Test() {
        HttpEntity<CardDto> httpEntity = new HttpEntity<>(card);
        ResponseEntity<BalanceDto> result = template
                .withBasicAuth(user, password)
                .exchange("/host/card/balance", HttpMethod.POST, httpEntity, BalanceDto.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(balance, result.getBody());
    }


}