package com.example.SpringBootThird.controller;

import com.example.SpringBootThird.model.Greeting;
import com.example.SpringBootThird.service.GreetingService;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> UC4

@RestController
@RequestMapping("/greeting")
public class GreetingController {
    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

<<<<<<< HEAD
    @GetMapping("/message")
    public String getMessage(@RequestParam(required = false) String firstName,
                             @RequestParam(required = false) String lastName) {
        return greetingService.getGreetingMessage(firstName, lastName);
=======
    @PostMapping("/save")
    public Greeting saveGreeting(@RequestParam String message) {
        return greetingService.saveGreeting(message);
>>>>>>> UC4
    }
}
