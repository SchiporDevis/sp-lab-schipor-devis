package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.difexamples.ClientComponent;

@RestController
public class HelloController {

    private final ClientComponent clientComponent;

    // Injectarea automată a ClientComponent în controller
    @Autowired
    public HelloController(ClientComponent clientComponent) {
        this.clientComponent = clientComponent;
    }

    @GetMapping("/")
    public String index() {
        return "Hello from Spring Boot! Injectat: " + clientComponent;
    }
}