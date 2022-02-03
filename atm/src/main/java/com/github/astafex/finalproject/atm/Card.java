package com.github.astafex.finalproject.atm;

import lombok.Value;

import java.time.LocalDate;

@Value
public class Card {
    String numberCard;
    String holderName;
    LocalDate expireDate;
    String PINCode;
}
