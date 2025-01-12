package com.horizonbank.Horizon.Bank.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;

import com.horizonbank.Horizon.Bank.Entities.User;
import com.horizonbank.Horizon.Bank.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")

public class UserControllers {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUserController(@RequestBody @Valid User user) {
        try {
            User userCreated = userService.createUser(user);
            return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Deshabilita CSRF para simplificar pruebas iniciales
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permite el acceso a todos los endpoints sin autenticación
                )
                .httpBasic().disable(); // Deshabilita la autenticación básica

        return http.build();
    }

}
