package com.github.astafex.finalproject.server.entity;

import com.github.astafex.finalproject.atm.entity.Card;
import lombok.Value;

import java.util.Set;

@Value
public class Account {
    Set<Card> cards;
    String number;
    Balance balance;
}
