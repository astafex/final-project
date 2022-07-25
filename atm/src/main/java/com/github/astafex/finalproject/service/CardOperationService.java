package com.github.astafex.finalproject.service;

import com.github.astafex.finalproject.dto.BalanceDto;
import com.github.astafex.finalproject.dto.CardDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Service
//TODO сделать интерфейс
public class CardOperationService {
    private static final Logger LOG = LoggerFactory.getLogger(CardOperationService.class);

    private final String RESOURCE_URL;
    private final String ENCODE_BASIC_AUTH;

    public CardOperationService(@Value("${resource.url}") String RESOURCE_URL,
                                @Value("${resource.login}") String RESOURCE_LOGIN,
                                @Value("${resource.password}") String RESOURCE_PASSWORD) {
        this.RESOURCE_URL = RESOURCE_URL;
        //TODO
        this.ENCODE_BASIC_AUTH = HttpHeaders.encodeBasicAuth(RESOURCE_LOGIN, RESOURCE_PASSWORD, StandardCharsets.US_ASCII);
    }

    /**
     * Метод выполняет http-запрос к серверу на получение баланса по номеру карты клиента
     *
     * @param number номер карты клиента
     * @param PIN    пин-код карты
     * @return объект класса {@link BalanceDto}, служащий для передачи между слоями приложения
     * @throws HttpClientErrorException бросается в случаях некорректного/исключительного ответа сервера (например карта не прошла проверку на стороне сервера)
     * @throws ResourceAccessException  бросается, если сервер недоступен
     */
    public BalanceDto getBalance(String number, int PIN) {
        final String uri = RESOURCE_URL + "/card/balance";
        CardDto card = CardDto.builder().number(number).PIN(PIN).build();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setBasicAuth(ENCODE_BASIC_AUTH);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<CardDto> httpRequest = new HttpEntity<>(card, httpHeaders);
        LOG.info(httpRequest.toString());

        try {
            ResponseEntity<BalanceDto> response = restTemplate.exchange(uri, HttpMethod.POST, httpRequest, BalanceDto.class);
            LOG.info(response.toString());
            return response.getBody();
        } catch (RuntimeException ex) {
            LOG.error("Exception thrown: ", ex.fillInStackTrace());
            throw ex;
        }
    }
}
