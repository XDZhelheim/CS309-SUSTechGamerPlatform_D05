package com.example.springproject.service;

import com.example.springproject.domain.Game;

public interface GameService {
    void save(Game game);

    Game getGame(String name);

    void delete(Game game);
}
