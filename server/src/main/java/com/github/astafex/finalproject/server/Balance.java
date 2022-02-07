package com.github.astafex.finalproject.server;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Balance {
    BigDecimal balance;
    Currency currency;
}
