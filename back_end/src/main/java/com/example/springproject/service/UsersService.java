package com.example.springproject.service;

import com.example.springproject.domain.Users;

public interface UsersService {
    void save(Users users);

    boolean checkLogin(Users userInfo);

    Users findByUsername(String username);

    void delete(Users users);
}
