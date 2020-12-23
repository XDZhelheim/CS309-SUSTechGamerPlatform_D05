package com.example.springproject.service;

import com.example.springproject.domain.Users;

import java.util.List;

public interface UsersService {
    void save(Users users);

    boolean checkLogin(Users userInfo);

    Users findByUsername(String username);

    List<Users> findAllUsers();

    void delete(Users users);
}
