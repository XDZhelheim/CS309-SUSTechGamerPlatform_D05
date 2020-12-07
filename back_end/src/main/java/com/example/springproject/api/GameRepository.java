package com.example.springproject.api;

import com.example.springproject.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game findGameByName(String name);
}
