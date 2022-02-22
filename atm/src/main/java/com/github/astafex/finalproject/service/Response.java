package com.github.astafex.finalproject.service;

import lombok.Value;

@Value
public class Response {
    int statusCode;
    String status;
    String message;
}
