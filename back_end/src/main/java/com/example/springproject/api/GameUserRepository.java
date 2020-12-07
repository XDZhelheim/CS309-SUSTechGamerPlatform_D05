package com.example.springproject.api;

import com.example.springproject.domain.Game;
import com.example.springproject.domain.GameUser;
import com.example.springproject.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameUserRepository extends JpaRepository<GameUser, Long> {
    List<GameUser> findGameUserByGame(Game game);

    List<GameUser> findGameUserByUsers(Users users);

    GameUser findGameUserByUsersAndGame(Users users, Game game);
}
