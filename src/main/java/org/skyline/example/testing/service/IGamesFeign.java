package org.skyline.example.testing.service;

import org.skyline.example.testing.entity.GameF;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "games-f", url = "https://www.freetogame.com/api/games")
public interface IGamesFeign {
    @RequestMapping(method = RequestMethod.GET)
    List<GameF> getGames(@RequestParam(name = "platform") String platform);
}
