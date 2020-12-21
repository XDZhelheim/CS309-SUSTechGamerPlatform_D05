package com.example.springproject.service;

import com.example.springproject.api.UsersRepository;
import com.example.springproject.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void save(Users users) {
        users.setAccount(0);
        users.setCreateDate(new Date(System.currentTimeMillis()));
        users.setRole('U');
        usersRepository.save(users);
    }

    @Override
    public boolean checkLogin(Users users) {
        return usersRepository.findUserByNameAndPassword(users.getName(), users.getPassword()) != null;
    }

    @Override
    public Users findByUsername(String username) {
        return usersRepository.findUserByName(username);
    }

    @Override
    public void delete(Users users) {
        usersRepository.delete(users);
    }
}
