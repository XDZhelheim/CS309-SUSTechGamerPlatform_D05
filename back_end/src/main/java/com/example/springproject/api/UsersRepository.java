package com.example.springproject.api;

import com.example.springproject.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findUserByName(String name);

    Users findUserByNameAndPassword(String name, String password);
}
