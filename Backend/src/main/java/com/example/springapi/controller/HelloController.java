package com.example.springapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/com/example/springapi/api")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Conectado ao Backend Java com sucesso! vamos jogar um video game?";
    }
}