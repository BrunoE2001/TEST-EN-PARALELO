package org.skyline.example.testing.controller;

import jakarta.validation.Valid;
import org.skyline.example.testing.dtos.GameDto;
import org.skyline.example.testing.entity.Game;
import org.skyline.example.testing.entity.GameF;
import org.skyline.example.testing.service.IGameService;
import org.skyline.example.testing.service.IGamesFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/api/games")
public class GameController {
    @Autowired
    private IGameService gameService;

    @GetMapping("count")
    public ResponseEntity<Long> GetCountAllGames() {
        return new ResponseEntity<>(this.gameService.countAllGames(), OK);
    }

    @PostMapping
    public ResponseEntity<Game> SaveNewGames(@Valid @RequestBody GameDto dto) {
        return new ResponseEntity<>(this.gameService.createGame(dto), CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GameF>> GetAllGames(@RequestParam(name = "platform") String platform) {
        return new ResponseEntity<>(this.gameService.getGamesForPlatform(platform), OK);
    }
}
