package zhegalov.course.work.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.GameSettings;
import zhegalov.course.work.model.Question;
import zhegalov.course.work.respositories.GameSessionRepository;

@RequiredArgsConstructor
@Service
public class GameSessionServiceImpl implements GameSessionService {
    private final GameSessionRepository gameSessionRepository;

	@Override
	public GameSession create(GameSettings gameSettings) {
        return gameSessionRepository.save(new GameSession());
	}

	@Override
	public Question getQuestion(GameSession gameSession) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<GameSession> getGameSession(String id) {
        return gameSessionRepository.findById(id);
	}

}

