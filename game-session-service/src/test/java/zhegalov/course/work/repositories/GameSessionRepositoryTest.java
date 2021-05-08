package zhegalov.course.work.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.respositories.GameSessionRepository;

@ComponentScan("zhegalov.course.work.respositories")
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class GameSessionRepositoryTest {
    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Test
    void shouldSaveNewGameSession() {
        final var savedSession = gameSessionRepository.save(new GameSession());
        assertThat(savedSession.getId()).isNotNull().isNotBlank();
    }
}
