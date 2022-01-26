package com.github.astafex.finalproject.server.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

import java.util.Set;
import java.util.stream.Stream;

@Value
public class Client {
    @Getter(AccessLevel.NONE)
    Set<Account> accounts;
    long clientId;
    String fullName;

    public Stream<Account> getAccounts() {
        return accounts.stream();
    }
}
