package com.example.SpringBootThird.service;

import com.example.SpringBootThird.model.Greeting;
import com.example.SpringBootThird.repository.GreetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public class GreetingService {
    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    // Fetch Greeting by ID
    public Greeting getGreetingById(Long id) {
        return greetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Greeting not found with ID: " + id));
    }
    public Greeting saveGreeting(Greeting greeting) {
        return greetingRepository.save(greeting);
    }
    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }
    public Greeting updateGreeting(Long id, Greeting newGreeting) {
        Greeting existingGreeting = greetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Greeting not found with ID: " + id));
        existingGreeting.setMessage(newGreeting.getMessage());
        return greetingRepository.save(existingGreeting);
    }
    public void deleteGreeting(Long id) {
        if (!greetingRepository.existsById(id)) {
            throw new RuntimeException("Greeting not found with ID: " + id);
        }
        greetingRepository.deleteById(id);
    }
}
