package com.github.astafex.finalproject;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class Card {
    String number;
    String holderName;
    LocalDate expirationDate;
    String PINNumber;
}
