package com.example.springproject.service;

import com.example.springproject.domain.Game;
import com.example.springproject.domain.GameUser;
import com.example.springproject.domain.Users;

import java.util.List;

public interface GameUserService {
    void save(GameUser gameUser);

    void delete(Users users, Game game);

    List<GameUser> listAllGamesForCreditAs(Users users, char creditAs);

    List<GameUser> getGameOwners(Game game);

    List<GameUser> getOwnerUsers(Users users);

    GameUser getRecord(Users users, Game game);

    double averageScore(Game game);
}
