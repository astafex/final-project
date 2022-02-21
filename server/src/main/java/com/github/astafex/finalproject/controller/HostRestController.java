package com.github.astafex.finalproject.controller;

import com.github.astafex.finalproject.dto.AccountDto;
import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.dto.CardDto;
import com.github.astafex.finalproject.service.AccountService;
import com.github.astafex.finalproject.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/host")
@AllArgsConstructor
public class HostRestController {
    private final CardService cardService;
    private final AccountService accountService;

    @PostMapping("/card/balance")
    public BalanceDto getBalanceByCard(@RequestBody CardDto cardDto) {
        AccountDto accountDto = cardService.getAccount(cardDto);
        return accountService.getBalance(accountDto);
    }

    @GetMapping("/status")
    public String getStatusHost() {
        return "{host: \"available\"}";
    }
}
