package com.example.springproject.api;

import com.example.springproject.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserInfo, Integer> {
    public UserInfo findUserByUsername(String username);
    public UserInfo findUserByUsernameAndPassword(String username, String password);
}
