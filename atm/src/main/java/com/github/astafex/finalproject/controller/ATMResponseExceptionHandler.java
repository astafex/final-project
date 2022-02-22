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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;

@ControllerAdvice
public class ATMResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = HttpClientErrorException.class)
    protected ResponseEntity<Object> remoteResponse(HttpClientErrorException httpError, WebRequest request) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Response response = new Response(
                httpError.getStatusCode().value(),
                httpError.getStatusCode().name(),
                httpError.getResponseBodyAsString()
        );
        String bodyOfResponse = new ObjectMapper().writeValueAsString(response);
        return this.handleExceptionInternal(httpError, bodyOfResponse, httpHeaders, httpError.getStatusCode(), request);
    }

    @ExceptionHandler(value = ConnectException.class)
    protected ResponseEntity<Object> remoteResponse(ConnectException connectError, WebRequest request) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.REQUEST_TIMEOUT;
        Response response = new Response(
                httpStatus.value(),
                httpStatus.name(),
                "Удаленный сервер недоступен"
        );
        String bodyOfResponse = new ObjectMapper().writeValueAsString(response);
        return this.handleExceptionInternal(connectError, bodyOfResponse, httpHeaders, httpStatus, request);
    }
}
