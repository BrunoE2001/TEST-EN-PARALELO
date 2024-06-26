package org.skyline.example.testing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.Mock;
import org.skyline.example.testing.entity.GameF;
import org.skyline.example.testing.repository.IGameRepository;
import org.skyline.example.testing.service.GameService;
import org.skyline.example.testing.service.IGameService;
import org.skyline.example.testing.service.IGamesFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "testing")
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IGameRepository gameRepository;
    @MockBean
    private IGamesFeign gamesFeign;
    private GameService gameService;


    @BeforeEach
    void setUp() {
        gameService = new GameService(gameRepository, gamesFeign);
    }

    private IGameService GameService;
//    @SqlGroup({
//        @Sql(value = "classpath:sql/clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
//        @Sql(value = "classpath:sql/create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
//        @Sql(value = "classpath:sql/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//    })
    private static final Lock lock = new ReentrantLock();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setupDatabase() {
        lock.lock();
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS games;");
            jdbcTemplate.execute("CREATE TABLE games (\n" +
                    "    id bigint PRIMARY KEY,\n" +
                    "    title varchar(255) NOT NULL,\n" +
                    "    genre varchar(255) NOT NULL,\n" +
                    "    platform varchar(255) NOT NULL\n" +
                    ");");
            jdbcTemplate.execute("INSERT INTO games (id, title, genre, platform) VALUES\n" +
                    "  (1, 'The Legend of Zelda: Breath of the Wild', 'Action/Adventure', 'Nintendo Switch'),\n" +
                    "  (2, 'Minecraft', 'Sandbox', 'Multiplatform'),\n" +
                    "  (3, 'Among Us', 'Multiplayer', 'Multiplatform'),\n" +
                    "  (4, 'Fortnite', 'Battle Royale', 'Multiplatform'),\n" +
                    "  (5, 'Overwatch', 'FPS', 'Multiplatform');");
        } finally {
            lock.unlock();
        }
    }

//    @Test
    @RepeatedTest(50)
    @Execution(ExecutionMode.CONCURRENT)
    void getCountAllGames() throws Exception {
        System.out.println("currentThread => " + Thread.currentThread().getId());
        ResultActions actions = this.mockMvc.perform(get("http://localhost:8090/v1/api/games/count")).andExpect(status().is2xxSuccessful());
        String jsonResponse = actions.andReturn().getResponse().getContentAsString();

        assertNotNull(actions);
        assertEquals(Integer.parseInt(jsonResponse), 5);
    }

//    @Test
    @RepeatedTest(10)
    @Execution(ExecutionMode.CONCURRENT)
    void getAllGames() {
        List<GameF> games = new ArrayList<>();
        when(this.gamesFeign.getGames("pc")).thenReturn(games);
//        verify(gamesFeign, times(1)).getGames("pc");
    }

    @Test
    void invokeMethods() throws Exception {
        gameService.getGamesForPlatform("pc");

        verify(gamesFeign).getGames("pc");
    }
}