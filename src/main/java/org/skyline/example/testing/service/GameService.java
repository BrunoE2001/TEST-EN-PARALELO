package org.skyline.example.testing.service;

import lombok.AllArgsConstructor;
import org.skyline.example.testing.dtos.GameDto;
import org.skyline.example.testing.entity.Game;
import org.skyline.example.testing.entity.GameF;
import org.skyline.example.testing.repository.IGameRepository;
import org.skyline.example.testing.util.IUniqueValueChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GameService implements IGameService {

    @Autowired
    private IGameRepository gameRepository;
    @Autowired
    private IGamesFeign gamesFeign;

    @Override
    public long countAllGames() {
        return this.gameRepository.count();
    }

    @Override
    public Game createGame(GameDto game) {
        Game newGame = new Game();
        newGame.setTitle(game.getTitle());
        newGame.setGenre(game.getGenre());
        newGame.setPlatform(game.getPlatform());

        return this.gameRepository.save(newGame);
    }

    public List<GameF> getGamesForPlatform(String plataform) {
        return gamesFeign.getGames(plataform);
    }
}
