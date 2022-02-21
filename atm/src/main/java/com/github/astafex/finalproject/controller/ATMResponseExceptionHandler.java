package com.github.astafex.finalproject.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ATMResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = HttpClientErrorException.class)
    protected ResponseEntity<Object> remoteResponse(HttpClientErrorException httpError, WebRequest request) {
        String bodyOfResponse = httpError.getMessage();
        return this.handleExceptionInternal(httpError, bodyOfResponse, new HttpHeaders(), httpError.getStatusCode(), request);
    }
}
