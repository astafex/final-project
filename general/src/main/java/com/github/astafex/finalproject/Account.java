package com.github.astafex.finalproject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

import java.util.Set;
import java.util.stream.Stream;

@Value
public class Account {
    String number;
    Balance balance;
    @Getter(AccessLevel.NONE)
    Set<Card> cards;

    public Stream<Card> getCards() {
        return cards.stream();
    }
}
