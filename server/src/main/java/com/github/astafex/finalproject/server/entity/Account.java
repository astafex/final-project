package com.github.astafex.finalproject.server.entity;

import com.github.astafex.finalproject.atm.entity.Card;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

import java.util.Set;
import java.util.stream.Stream;

@Value
public class Account {
    @Getter(AccessLevel.NONE)
    Set<Card> cards;
    String number;
    Balance balance;

    public Stream<Card> getCards() {
        return cards.stream();
    }
}
