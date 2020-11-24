package com.example.springproject.service;

import com.example.springproject.domain.UserInfo;

public interface UserService {
    public void save(UserInfo userInfo);
    public boolean checkLogin(UserInfo userInfo);
    public UserInfo findByUsername(String username);
}
