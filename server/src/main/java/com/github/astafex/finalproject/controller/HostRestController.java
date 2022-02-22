package com.github.astafex.finalproject.controller;

import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.dto.CardDto;
import com.github.astafex.finalproject.service.CardService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/host")
@AllArgsConstructor
public class HostRestController {
    private static final Logger LOG = LoggerFactory.getLogger(CardService.class);
    private final CardService cardService;

    @PostMapping("/card/balance")
    public BalanceDto getBalanceByCard(@RequestBody CardDto cardDto) {
        LOG.info(cardDto.toString());
        BalanceDto balanceDto = cardService.getBalance(cardDto.getNumber(), cardDto.getPIN());
        LOG.info(balanceDto.toString());
        return balanceDto;
    }

    @GetMapping("/status")
    public String getStatusHost() {
        return "{host: \"available\"}";
    }
}
