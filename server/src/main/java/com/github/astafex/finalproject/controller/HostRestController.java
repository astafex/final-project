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
    //TODO interface
    private final CardService cardService;

    /**
     * Метод контроллера сервера вызывает сервис карт, для получения данных о балансе, после чего возвращает его
     *
     * @param cardDto объект карты предназначеный для передачи между приложений/слоями приложений
     * @return объект класса {@link BalanceDto}, служащий для передачи между слоями приложения
     */
    @PostMapping("/card/balance")
    //TODO ResponseEntity
    public BalanceDto getBalanceByCard(@RequestBody CardDto cardDto) {
        LOG.info(cardDto.toString());
        BalanceDto balanceDto = cardService.getBalance(cardDto.getNumber(), cardDto.getPIN());
        LOG.info(balanceDto.toString());
        return balanceDto;
    }

    @GetMapping("/status")
    public String getStatusHost() {
        return "{\"host\":\"available\"}";
    }
}
