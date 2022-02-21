package com.github.astafex.finalproject.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
@NoArgsConstructor
public class CardCheckException extends RuntimeException {
    public CardCheckException(String message) {
        super(message);
    }
}
