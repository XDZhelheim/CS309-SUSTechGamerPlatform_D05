package com.example.springproject.service;

import com.example.springproject.api.UserRepository;
import com.example.springproject.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Deprecated
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(UserInfo userInfo) {
        userRepository.save(userInfo);
    }

    @Override
    public boolean checkLogin(UserInfo userInfo) {
        UserInfo u = userRepository.findUserByUsernameAndPassword(userInfo.getUsername(), userInfo.getPassword());
        return u != null;
    }

    @Override
    public UserInfo findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
