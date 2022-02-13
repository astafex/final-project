package com.github.astafex.finalproject;

import org.springframework.web.bind.annotation.*;

@RestController
public class ServerRestController {
    @GetMapping("/{id}")
    public String getStatusInfo(@PathVariable int id) {

        return "{server-" + id + " : success}";
    }

    @PostMapping
    public String getCardBalance(@RequestBody Card card) {
        return card.getNumber();
    }
}
