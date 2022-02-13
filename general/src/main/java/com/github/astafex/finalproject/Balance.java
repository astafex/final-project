package com.github.astafex.finalproject;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Balance {
    BigDecimal amount;
    Currency currency;
}
