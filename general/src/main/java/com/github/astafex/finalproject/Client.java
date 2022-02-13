package com.github.astafex.finalproject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

import java.util.Set;
import java.util.stream.Stream;

@Value
public class Client {
    long clientId;
    String firstName;
    String lastName;
    @Getter(AccessLevel.NONE)
    Set<Account> accounts;

    public Stream<Account> getAccounts() {
        return accounts.stream();
    }
}
