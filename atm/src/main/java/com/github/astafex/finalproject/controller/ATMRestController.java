package com.github.astafex.finalproject.controller;

import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.service.ATMService;
import com.github.astafex.finalproject.service.Response;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ATM")
@AllArgsConstructor
public class ATMRestController {
    private static final Logger LOG = LoggerFactory.getLogger(ATMRestController.class);
    private final ATMService atmService;

    @GetMapping("/balance/card")
    public Response getBalance(@RequestParam("number") String number,
                               @RequestParam("pin") int PIN) {
        LOG.info("Card [number: {}, PIN: {}]", number, PIN);
        BalanceDto balanceDto = atmService.getBalance(number, PIN);
        String bodyOfResponse = String.format("Баланс карты: %s %s", balanceDto.getAmount(), balanceDto.getCurrency());
        Response response = new Response(HttpStatus.OK.value(), HttpStatus.OK.name(), bodyOfResponse);
        LOG.info(response.toString());
        return response;
    }
}
