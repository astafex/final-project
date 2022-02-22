package com.github.astafex.finalproject.service;

import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.dto.CardDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Service
public class ATMService {
    private static final Logger LOG = LoggerFactory.getLogger(ATMService.class);

    private final String RESOURCE_URL;
    private final String ENCODE_BASIC_AUTH;

    public ATMService(@Value("${resource.url}") String RESOURCE_URL,
                      @Value("${resource.login}") String RESOURCE_LOGIN,
                      @Value("${resource.password}") String RESOURCE_PASSWORD) {
        this.RESOURCE_URL = RESOURCE_URL;
        this.ENCODE_BASIC_AUTH = HttpHeaders.encodeBasicAuth(RESOURCE_LOGIN, RESOURCE_PASSWORD, StandardCharsets.US_ASCII);
    }

    public BalanceDto getBalance(String number, int PIN) {
        final String uri = RESOURCE_URL + "/card/balance";
        CardDto card = new CardDto(number, PIN);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setBasicAuth(ENCODE_BASIC_AUTH);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<CardDto> httpRequest = new HttpEntity<>(card, httpHeaders);
        LOG.info(httpRequest.toString());

        try {
            ResponseEntity<BalanceDto> response = restTemplate.exchange(uri, HttpMethod.POST, httpRequest, BalanceDto.class);
            LOG.info(response.toString());
            return response.getBody();
        } catch (RuntimeException ex) {
            LOG.error("Exception thrown: ", ex.fillInStackTrace());
            throw ex;
        }
    }
}
