package zhegalov.course.work.service;

import java.util.Optional;

import zhegalov.course.work.model.GameSession;

public interface GameSessionService {

    GameSession create(String jsonData);

    GameSession save(GameSession gameSession);

    Optional<GameSession> getGameSession(String id);

    boolean isSessionComplete(GameSession gameSession);

}
