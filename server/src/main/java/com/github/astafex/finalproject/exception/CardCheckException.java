package com.github.astafex.finalproject.exception;

/**
 * Исключение бросается, если не прошла любая из проверок над действующей банковской картой.
 */
public class CardCheckException extends RuntimeException {
    public CardCheckException(String message) {
        super(message);
    }
}
