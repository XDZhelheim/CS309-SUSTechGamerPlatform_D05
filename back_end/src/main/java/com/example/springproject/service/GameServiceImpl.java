package com.example.springproject.service;

import com.example.springproject.api.GameRepository;
import com.example.springproject.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public void save(Game game) {
        game.setCreateTime(new Date(System.currentTimeMillis()));
        game.setId(gameRepository.count() + 1);
        gameRepository.save(game);
    }

    @Override
    public Game getGame(String name) {
        return gameRepository.findGameByName(name);
    }

    @Override
    public void delete(Game game) {
        gameRepository.delete(game);
    }

    @Override
    public List<Game> getAllGame() {
        return gameRepository.findAll();
//        Game[] games = new Game[(int)gameRepository.count()];
//        for (int i=0;i<games.length;i++){
//            games[i] = gameRepository.findAll();
//        }
    }


}
