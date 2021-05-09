package zhegalov.course.work.service;

import java.util.Optional;

import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.GameSettings;
import zhegalov.course.work.model.Question;

public interface GameSessionService {

    GameSession create(GameSettings gameSettings);

    Optional<GameSession> getGameSession(String id);

    boolean isSessionComplete(GameSession gameSession);

    //TODO replace to question service
    Question getQuestion(GameSession gameSession);
}

