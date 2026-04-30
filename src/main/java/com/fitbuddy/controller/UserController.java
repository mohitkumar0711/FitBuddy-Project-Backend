package com.fitbuddy.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUsers() {
        return "User list";
    }

    @PostMapping
    public String createUser() {
        return "User created";
    }
}
