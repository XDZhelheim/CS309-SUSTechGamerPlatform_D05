package com.example.springproject.service;

import com.example.springproject.api.GameUserRepository;
import com.example.springproject.domain.Game;
import com.example.springproject.domain.GameUser;
import com.example.springproject.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameUserServiceImpl implements GameUserService {
    @Autowired
    private GameUserRepository gameUserRepository;

    @Override
    public void buy(GameUser gameUser) {
        gameUserRepository.save(gameUser);
    }

    @Override
    public void delete(Users users, Game game) {
        gameUserRepository.delete(getRecord(users, game));
    }

    @Override
    public List<GameUser> listAllGamesForCreditAs(Users users, char creditAs) {
        return gameUserRepository.findGameUserByUsersAndCreditAs(users, creditAs);
    }

    @Override
    public List<GameUser> getGameOwners(Game game) {
        return gameUserRepository.findGameUserByGame(game);
    }

    @Override
    public List<GameUser> getOwnerUsers(Users users) {
        return gameUserRepository.findGameUserByUsers(users);
    }

    @Override
    public GameUser getRecord(Users users, Game game) {
        return gameUserRepository.findGameUserByUsersAndGame(users, game);
    }
}
