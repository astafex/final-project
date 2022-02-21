package com.github.astafex.finalproject.service;

import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.dto.CardDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Service
public class CardOperation {
    @Value("${resource.url}")
    private String RESOURCE_URL;
    @Value("${resource.login}")
    private String RESOURCE_LOGIN;
    @Value("${resource.password}")
    private String RESOURCE_PASSWORD;

    public BalanceDto getBalance(String number, int PIN) {
        CardDto card = new CardDto(number, PIN);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        String encodeBasicAuth = HttpHeaders.encodeBasicAuth(RESOURCE_LOGIN, RESOURCE_PASSWORD, StandardCharsets.US_ASCII);

        httpHeaders.setBasicAuth(encodeBasicAuth);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<CardDto> httpRequest = new HttpEntity<>(card, httpHeaders);

        ResponseEntity<BalanceDto> response = restTemplate.exchange(RESOURCE_URL + "/card/balance", HttpMethod.POST, httpRequest, BalanceDto.class);

        return response.getBody();
    }
}
