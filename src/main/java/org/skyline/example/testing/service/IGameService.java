package org.skyline.example.testing.service;

import org.skyline.example.testing.dtos.GameDto;
import org.skyline.example.testing.entity.Game;
import org.skyline.example.testing.entity.GameF;

import java.util.List;

public interface IGameService {
    long countAllGames();
    Game createGame(GameDto game);
    List<GameF> getGamesForPlatform(String plataform);
}
