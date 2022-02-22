package com.github.astafex.finalproject.controller;

import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.service.Response;
import com.github.astafex.finalproject.service.ATMService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ATM")
@AllArgsConstructor
public class ATMRestController {
    private ATMService atmService;

    @GetMapping("/balance/card")
    public Response getBalance(@RequestParam("number") String number,
                               @RequestParam("pin") int PIN) {
        BalanceDto balanceDto = atmService.getBalance(number, PIN);
        String bodyOfResponse = String.format("Баланс карты: %s %s", balanceDto.getAmount(), balanceDto.getCurrency());
        return new Response(HttpStatus.OK.value(), HttpStatus.OK.name(), bodyOfResponse);
    }
}
