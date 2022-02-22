package com.github.astafex.finalproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.astafex.finalproject.service.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ATMResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = HttpClientErrorException.class)
    protected ResponseEntity<Object> remoteResponse(HttpClientErrorException httpError, WebRequest request) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        String bodyOfResponse = bodyOfResponse(
                httpError.getStatusCode(),
                httpError.getResponseBodyAsString());
        return this.handleExceptionInternal(httpError, bodyOfResponse, httpHeaders, httpError.getStatusCode(), request);
    }

    @ExceptionHandler(value = ResourceAccessException.class)
    protected ResponseEntity<Object> connectTimeout(ResourceAccessException connectError, WebRequest request) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpStatus httpStatus = HttpStatus.REQUEST_TIMEOUT;
        String bodyOfResponse = bodyOfResponse(
                httpStatus,
                "Удаленный сервер недоступен");
        return this.handleExceptionInternal(connectError, bodyOfResponse, httpHeaders, httpStatus, request);
    }

    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<Object> internalServerError(RuntimeException exception, WebRequest request) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String bodyOfResponse = bodyOfResponse(
                httpStatus,
                "Неизвестная ошибка");
        return this.handleExceptionInternal(exception, bodyOfResponse, httpHeaders, httpStatus, request);
    }

    private String bodyOfResponse(HttpStatus httpStatus, String message) throws JsonProcessingException {
        Response response = new Response(
                httpStatus.value(),
                httpStatus.name(),
                message);
        return new ObjectMapper().writeValueAsString(response);
    }
}
