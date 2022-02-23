package com.github.astafex.finalproject.exception;

/**
 * Исключение броается, если не найдена карта с указаными данными
 */
public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String message) {
        super(message);
    }
}
