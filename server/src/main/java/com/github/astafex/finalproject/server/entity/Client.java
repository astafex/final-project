package com.github.astafex.finalproject.server.entity;

import lombok.Value;

import java.util.Set;

@Value
public class Client {
    Long clientId;
    String fullName;
    Set<Account> accounts;
}
