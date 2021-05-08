package zhegalov.course.work.service;

import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.GameSettings;

public interface GameSessionService {

    GameSession create(GameSettings gameSettings);

}

