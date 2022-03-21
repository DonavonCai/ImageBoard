package com.donavon.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/login")
    public String hello() {
        System.out.println("Got request!!!");
        return "Hello from Spring Boot!";
    }
}
