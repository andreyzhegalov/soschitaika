package zhegalov.course.work.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.GameSettings;
import zhegalov.course.work.respositories.GameSessionRepository;

@SpringBootTest
public class GameSessionServiceTest {
    @Import(GameSessionServiceImpl.class)
    @Configuration
    public static class TestContext {
    }

    @Autowired
    private GameSessionService sessionService;

    @MockBean
    private GameSessionRepository sessionRepository;

    @Test
    void shouldCreateNewGameSessionBySettings() {
        given(sessionRepository.save(any())).willReturn(new GameSession());

        final var newSession = sessionService.create(new GameSettings());

        then(sessionRepository).should().save(any());

        assertThat(newSession).isNotNull();
    }
}
