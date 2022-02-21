package com.github.astafex.finalproject.controller;

import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.service.CardOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ATM/card")
@AllArgsConstructor
public class ATMRestController {
    private CardOperation operation;

    @GetMapping("/balance")
    public BalanceDto getBalance(@RequestParam("number") String number,
                                 @RequestParam("PIN") int PIN) {
        return operation.getBalance(number, PIN);
    }
}
