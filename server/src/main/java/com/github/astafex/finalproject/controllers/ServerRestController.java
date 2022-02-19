package com.github.astafex.finalproject.controllers;

import com.github.astafex.finalproject.Card;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServerRestController {
    @GetMapping("/{id}")
    public String getStatusInfo(@PathVariable int id) {

        return "{server-" + id + " : success}";
    }

    @GetMapping("/user")
    public String userUri() {
        return "user";
    }

    @GetMapping("/admin")
    public String adminUri() {
        return "admin";
    }

    @PostMapping
    public String getCardBalance(@RequestBody Card card) {
        return card.getNumber();
    }
}
