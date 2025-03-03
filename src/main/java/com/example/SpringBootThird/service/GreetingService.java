package com.example.SpringBootThird.service;

import com.example.SpringBootThird.model.Greeting;
import com.example.SpringBootThird.repository.GreetingRepository;
import org.springframework.stereotype.Service;
public class GreetingService {
    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting saveGreeting(String message) {
        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }
}
