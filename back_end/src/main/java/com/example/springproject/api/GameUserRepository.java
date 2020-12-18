package com.example.springproject.api;

import com.example.springproject.domain.Game;
import com.example.springproject.domain.GameUser;
import com.example.springproject.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameUserRepository extends JpaRepository<GameUser, Long> {
    List<GameUser> findGameUserByUsersAndCreditAs(Users users, char creditAs);

    List<GameUser> findGameUserByGame(Game game);

    List<GameUser> findGameUserByUsers(Users users);

    GameUser findGameUserByUsersAndGame(Users users, Game game);

    @Query("select g from GameUser g where g.game = ?1 and g.score is not null ")
    List<GameUser> findByGameAndScoreExists(Game game);
}
