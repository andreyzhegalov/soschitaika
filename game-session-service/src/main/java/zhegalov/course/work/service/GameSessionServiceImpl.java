package zhegalov.course.work.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.GameSettings;
import zhegalov.course.work.respositories.GameSessionRepository;

@RequiredArgsConstructor
@Service
public class GameSessionServiceImpl implements GameSessionService {
    private final GameSessionRepository gameSessionRepository;

	@Override
	public GameSession create(GameSettings gameSettings) {
        return gameSessionRepository.save(new GameSession());
	}

}

