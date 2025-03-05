package com.example.SpringBootThird.service;

import com.example.SpringBootThird.dto.AuthUserDTO;
import com.example.SpringBootThird.dto.LoginDTO;
import com.example.SpringBootThird.model.AuthUser;
import com.example.SpringBootThird.repository.AuthUserRepo;
import com.example.SpringBootThird.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {
    private final AuthUserRepo authUserRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
   @Autowired
    public AuthService(AuthUserRepo authUserRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(AuthUserDTO authUserDTO) {
        if (authUserRepository.findByEmail(authUserDTO.getEmail()).isPresent()) {
            return "Email is already in use.";
        }
        AuthUser user = new AuthUser();
        user.setFirstName(authUserDTO.getFirstName());
        user.setLastName(authUserDTO.getLastName());
        user.setEmail(authUserDTO.getEmail());
        String pass=authUserDTO.getPassword();
        System.out.println("db pass"+ authUserDTO.getPassword());
        String encodedPassword = passwordEncoder.encode(authUserDTO.getPassword());
        System.out.println("🔹 Hashed Password Before Saving: " + encodedPassword);
        user.setPassword(encodedPassword); // Encode password
        System.out.println("db pass"+ authUserDTO.getPassword());
        authUserRepository.save(user);
        System.out.println("db pass"+ user.getPassword());
        System.out.println(user);
        return user.getPassword();
    }


    public String login(LoginDTO loginDTO) {
        Optional<AuthUser> userOptional = authUserRepository.findByEmail(loginDTO.getEmail());

        if (userOptional.isEmpty()) {
            System.out.println(" User not found in the database.");
            return "User not found!";
        }

        AuthUser user = userOptional.get();
        System.out.println("🔹 DEBUG: Found user with email: " + user.getEmail());
        System.out.println("🔹 DEBUG: Hashed password in DB: " + user.getPassword());

        // Check password


        boolean passwordMatches = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
        System.out.println("🔹 DEBUG: Password match result: " + passwordMatches);

        if (!passwordMatches) {
            return "Invalid email or password!";
        }

        return jwtUtil.generateToken(user.getEmail());
    }

}