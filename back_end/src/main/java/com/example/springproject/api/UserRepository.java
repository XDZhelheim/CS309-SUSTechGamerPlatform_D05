package com.example.springproject.api;

import com.example.springproject.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

@Deprecated
public interface UserRepository extends JpaRepository<UserInfo, Integer> {
    UserInfo findUserByUsername(String username);

    UserInfo findUserByUsernameAndPassword(String username, String password);
}
