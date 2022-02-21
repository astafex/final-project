package com.github.astafex.finalproject.controller;

import com.github.astafex.finalproject.dto.AccountDto;
import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.dto.CardDto;
import com.github.astafex.finalproject.service.AccountService;
import com.github.astafex.finalproject.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/host")
@AllArgsConstructor
public class ServerRestController {
    private final CardService cardService;
    private final AccountService accountService;

    @PostMapping("/accounts/account/balance")
    public BalanceDto getBalanceByCard(@RequestBody CardDto cardDto) {
        AccountDto accountDto = cardService.getAccount(cardDto);
        return accountService.getBalance(accountDto);
    }
}
