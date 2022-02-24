package com.github.astafex.finalproject.service;

import lombok.Value;

/**
 * Класс представляет из себя объект для единого формата ответа в случае успеха или возникших ошибок на стороне сервера/банкомата
 */
@Value
public class Response {
    int code;
    String status;
    String message;
}
