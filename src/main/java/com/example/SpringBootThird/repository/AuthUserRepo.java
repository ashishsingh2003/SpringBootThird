package com.example.SpringBootThird.repository;


import com.example.SpringBootThird.model.AuthUser;
import com.example.SpringBootThird.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AuthUserRepo extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByEmail(String email);
}