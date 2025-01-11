package com.horizonbank.Horizon.Bank.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.horizonbank.Horizon.Bank.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    List<User> findByLastName(String lasName);

}
