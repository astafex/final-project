package com.github.astafex.finalproject.controller;

import com.github.astafex.finalproject.exception.CardCheckException;
import com.github.astafex.finalproject.exception.CardNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HostResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = CardCheckException.class)
    protected ResponseEntity<Object> cardCheckConflict(CardCheckException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return this.handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = CardNotFoundException.class)
    protected ResponseEntity<Object> notFoundCardConflict(CardNotFoundException ex, WebRequest request) {
        String bodyOfResponse = "Карта не найдена";
        return this.handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
