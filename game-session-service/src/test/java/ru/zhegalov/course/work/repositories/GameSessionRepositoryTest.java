package ru.zhegalov.course.work.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

import ru.zhegalov.course.work.model.GameSession;
import ru.zhegalov.course.work.model.gamesettings.CalculatingGameSettings;
import ru.zhegalov.course.work.respositories.GameSessionRepository;
import ru.zhegalov.course.work.model.othergame.OtherGameSettings;

@DataMongoTest
@ComponentScan("zhegalov.course.work.respositories")
public class GameSessionRepositoryTest {
    @Autowired
    private GameSessionRepository gameSessionRepository;

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
        Assertions.assertThat(calculatingGameSessionFromRepository).isNotEmpty();
        Assertions.assertThat(calculatingGameSessionFromRepository.get().getGameSettings()).isNotNull()
                .isInstanceOf(CalculatingGameSettings.class).extracting("min").isEqualTo(3);

        final var otherGameSessionFromRepository = gameSessionRepository.findById(savedOtherGameSession.getId());
        Assertions.assertThat(otherGameSessionFromRepository).isNotEmpty();
        Assertions.assertThat(otherGameSessionFromRepository.get().getGameSettings()).isNotNull()
                .isInstanceOf(OtherGameSettings.class).extracting("someSettings").isEqualTo("some string");
    }
}
