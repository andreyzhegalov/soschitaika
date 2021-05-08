package zhegalov.course.work.service;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import zhegalov.course.work.model.GameSettings;


@SpringBootTest
public class GameSessionServiceTest {
    @Import(GameSessionServiceImpl.class)
    @Configuration
    public static class TestContext{
    }

    @Autowired
    private GameSessionService sessionService;

    @Test
    void shouldCreateNewGameSessionBySettings(){
        final var newSession = sessionService.create(new GameSettings());
        assertThat(newSession).isNotNull();
    }
}

