package com.github.astafex.finalproject.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.astafex.finalproject.Card;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Collections;

@RestController
@RequestMapping("/ATM")
public class ClientRestController {
    private final String RESOURCE_URL;
    private final String RESOURCE_LOGIN;
    private final String RESOURCE_PASSWORD;

    public ClientRestController(@Value("${resource.url}") String RESOURCE_URL,
                                @Value("${resource.login}") String RESOURCE_LOGIN,
                                @Value("${resource.password}") String RESOURCE_PASSWORD) {
        this.RESOURCE_URL = RESOURCE_URL;
        this.RESOURCE_LOGIN = RESOURCE_LOGIN;
        this.RESOURCE_PASSWORD = RESOURCE_PASSWORD;
    }

    @GetMapping("/{ATMId}/cards/card")
    public String getMessage(@PathVariable("ATMId") long ATMId,
                             @RequestParam("number") String number,
                             @RequestParam("holderName") String holderName,
                             @RequestParam("expirationDate") String expirationDate,
                             @RequestParam("PIN") String PIN) throws JsonProcessingException {

        if (ATMId != 1) {
            throw new RuntimeException("Bad request! Please reboot...");
        }

        String encodeBasicAuth = HttpHeaders.encodeBasicAuth(RESOURCE_LOGIN, RESOURCE_PASSWORD, StandardCharsets.US_ASCII);
        System.out.println(encodeBasicAuth);

        Card card = new Card(number, holderName, LocalDate.parse(expirationDate), PIN);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setBasicAuth(encodeBasicAuth);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Card> httpRequest = new HttpEntity<>(card, httpHeaders);

        System.out.println(httpRequest);
        System.out.println(new ObjectMapper().writeValueAsString(card));

        ResponseEntity<String> response = restTemplate.exchange(RESOURCE_URL, HttpMethod.POST, httpRequest, String.class);

        return response.getBody();
    }
}
