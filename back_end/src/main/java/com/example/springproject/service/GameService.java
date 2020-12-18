package com.example.springproject.service;

import com.example.springproject.domain.Game;

import java.util.List;

public interface GameService {
    void save(Game game);

    Game getGame(String name);

    void delete(Game game);

    List<Game> getAllGame();
}
