package com.github.astafex.finalproject;

import lombok.Value;

import java.time.LocalDate;

@Value
public class Card {
    String number;
    String holderName;
    LocalDate expirationDate;
    String PIN;
}
