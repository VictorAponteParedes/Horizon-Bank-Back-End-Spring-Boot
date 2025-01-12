package com.horizonbank.Horizon.Bank.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
