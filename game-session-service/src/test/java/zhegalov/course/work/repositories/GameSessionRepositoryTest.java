package zhegalov.course.work.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.gamesettings.CalculatingGameSettings;
import zhegalov.course.work.model.othergame.OtherGameSettings;
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

    @Test
    void shouldSaveSessionWithCorrectlySettingsType() {
        final var calculatingGameSession = new GameSession();
        final var calculationGameSettings = new CalculatingGameSettings();
        calculationGameSettings.setMin(3);
        calculatingGameSession.setGameSettings(calculationGameSettings);
        final var savedCalculatingGameSession = gameSessionRepository.save(calculatingGameSession);

        final var otherGameSession = new GameSession();
        final var otherGameSettings = new OtherGameSettings();
        otherGameSettings.setSomeSettings("some string");
        otherGameSession.setGameSettings(otherGameSettings);
        final var savedOtherGameSession = gameSessionRepository.save(otherGameSession);

        final var calculatingGameSessionFromRepository = gameSessionRepository
                .findById(savedCalculatingGameSession.getId());
        assertThat(calculatingGameSessionFromRepository).isNotEmpty();
        assertThat(calculatingGameSessionFromRepository.get().getGameSettings()).isNotNull()
                .isInstanceOf(CalculatingGameSettings.class).extracting("min").isEqualTo(3);

        final var otherGameSessionFromRepository = gameSessionRepository.findById(savedOtherGameSession.getId());
        assertThat(otherGameSessionFromRepository).isNotEmpty();
        assertThat(otherGameSessionFromRepository.get().getGameSettings()).isNotNull()
                .isInstanceOf(OtherGameSettings.class).extracting("someSettings").isEqualTo("some string");
    }
}
