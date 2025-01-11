package com.horizonbank.Horizon.Bank.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horizonbank.Horizon.Bank.Entities.User;
import com.horizonbank.Horizon.Bank.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
