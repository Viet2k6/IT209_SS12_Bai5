package com.example.session11ex5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Spring Boot CI/CD - Session 11 Exercise 5!";
    }
}
