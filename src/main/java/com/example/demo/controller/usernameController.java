package com.example.demo.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class usernameController {

    @GetMapping
    public String helloSpring(){
        return "<h1 style=color:gray;>Hello, Spring Boot!</h1>";
    }
}
