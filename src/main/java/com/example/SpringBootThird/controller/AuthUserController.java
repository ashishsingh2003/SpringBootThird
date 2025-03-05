
package com.example.SpringBootThird.controller;

import com.example.SpringBootThird.dto.AuthUserDTO;
import com.example.SpringBootThird.dto.LoginDTO;
import com.example.SpringBootThird.service.AuthService;
import com.example.SpringBootThird.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    private final AuthService authenticationService;

    @Autowired
    public AuthUserController(AuthService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AuthUserDTO authUserDTO) {
        try {
            return ResponseEntity.ok(authenticationService.register(authUserDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(authenticationService.login(loginDTO)); // FIXED method name
    }
}
