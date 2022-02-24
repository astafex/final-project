package com.github.astafex.finalproject.controller;

import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.service.CardOperation;
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
    private final CardOperation operation;

    /**Метод вызывает сервис операций по картам для получения баланса по карте, после чего переводит результат в единый формат ответа
     * @param number номер карты клиента
     * @param PIN пин-код карты
     * @return объект {@link Response} в формате json, который содержит статус операции
     */
    @GetMapping("/balance/card")
    public Response getBalanceByCard(@RequestParam("number") String number,
                                     @RequestParam("pin") int PIN) {
        LOG.info("Card [number: {}, PIN: {}]", number, PIN);
        BalanceDto balanceDto = operation.getBalance(number, PIN);
        String bodyOfResponse = String.format("Баланс карты: %s %s", balanceDto.getAmount(), balanceDto.getCurrency());
        Response response = new Response(HttpStatus.OK.value(), HttpStatus.OK.name(), bodyOfResponse);
        LOG.info(response.toString());
        return response;
    }
}
