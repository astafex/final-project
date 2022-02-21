//package com.github.astafex.finalproject.controllers;
//
//import com.github.astafex.finalproject.exception.CardCheckException;
//import com.github.astafex.finalproject.exception.CardNotFoundException;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@ControllerAdvice
//public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(value = {CardCheckException.class, CardNotFoundException.class})
//    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
//        StringBuilder bodyOfResponse = new StringBuilder("[ERROR] ");
//        if (ex instanceof CardCheckException) {
//            bodyOfResponse.append("Ошибка данных карты.");
//        }
//        if (ex instanceof CardNotFoundException) {
//            bodyOfResponse.append("Карта не найдена.");
//        }
//        return handleExceptionInternal(ex, bodyOfResponse,
//                new HttpHeaders(), HttpStatus.CONFLICT, request);
//    }
//}
