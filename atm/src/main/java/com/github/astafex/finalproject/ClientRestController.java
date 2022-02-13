package com.github.astafex.finalproject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

//TODO: Можно вместо RestTemplate попробовать WebClient (spring-boot-starter-webflux)

@RestController
@RequestMapping("/ATM")
public class ClientRestController {
    private final String RESOURCE_URL;
    private final String LOGIN;
    private final String PASSWORD;

    public ClientRestController(@Value("${resource.url}") String RESOURCE_URL,
                                @Value("${resource.login}") String LOGIN,
                                @Value("${resource.password}") String PASSWORD) {
        this.RESOURCE_URL = RESOURCE_URL;
        this.LOGIN = LOGIN;
        this.PASSWORD = PASSWORD;
    }

    @GetMapping("/{ATMId}/cards/card")
    public String getMessage(@PathVariable("ATMId") long ATMId,
                             @RequestParam("number") String number,
                             @RequestParam("holderName") String holderName,
                             @RequestParam("expirationDate") String expirationDate,
                             @RequestParam("PINNumber") String PINNumber) {

        if (ATMId != 1) {
            throw new RuntimeException("Bad request! Please reboot...");
        }

        Card card = Card.builder()
                .number(number)
                .holderName(holderName)
                .expirationDate(LocalDate.parse(expirationDate))
                .PINNumber(PINNumber)
                .build();

        RestTemplate restTemplate = new RestTemplate();
        //ResponseEntity<String> response = restTemplate.getForEntity(RESOURCE_URL + ATMId, String.class);
        HttpEntity<Card> request = new HttpEntity<>(card);

        ResponseEntity<Card> response = restTemplate.exchange(RESOURCE_URL, HttpMethod.POST, request, Card.class);


        return response.getBody().toString();
    }

}
