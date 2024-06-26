package org.skyline.example.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.Mock;
import org.skyline.example.testing.repository.IGameRepository;
import org.skyline.example.testing.service.GameService;
import org.skyline.example.testing.service.IGamesFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.testng.annotations.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "testing")
public class SecondParallelUnitTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private MockMvc mockMvc;

//    @SqlGroup({
//        @Sql(value = "classpath:sql/clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
//        @Sql(value = "classpath:sql/create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
//        @Sql(value = "classpath:sql/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//    })

//    @Test
//    @RepeatedTest(100)
//    @Execution(ExecutionMode.CONCURRENT)
//    public void first() throws Exception{
//        System.out.println("SecondParallelUnitTest first() start => " + Thread.currentThread().getId());
//        Thread.sleep(500);
//        System.out.println("SecondParallelUnitTest first() end => " + Thread.currentThread().getId());
//    }

//    @Test(threadPoolSize = 10, invocationCount = 15)
//    public void second() throws Exception{
//        System.out.println("SecondParallelUnitTest second() start => " + Thread.currentThread().getName());
//        Thread.sleep(500);
//        System.out.println("SecondParallelUnitTest second() end => " + Thread.currentThread().getName());
//    }
    private static final Lock lock = new ReentrantLock();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeMethod
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

    @Test(threadPoolSize = 100, invocationCount = 100)
    void getCountAllGames() throws Exception {
        System.out.println("currentThread => " + Thread.currentThread().getId());
        ResultActions actions = this.mockMvc.perform(get("http://localhost:8090/v1/api/games/count")).andExpect(status().is2xxSuccessful());
        String jsonResponse = actions.andReturn().getResponse().getContentAsString();

        assertNotNull(actions);
        assertEquals(Integer.parseInt(jsonResponse), 5);
    }
}
