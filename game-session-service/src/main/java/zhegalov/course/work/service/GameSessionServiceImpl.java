package zhegalov.course.work.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.GameSettings;
import zhegalov.course.work.respositories.GameSessionRepository;

@RequiredArgsConstructor
@Service
public class GameSessionServiceImpl implements GameSessionService {
    private final GameSessionRepository gameSessionRepository;
    private final QuestionService questionService;

    @Override
    public GameSession create(GameSettings gameSettings) {
        final var gameSession = new GameSession();
        gameSession.setGameSettings(gameSettings);
        return gameSessionRepository.save(gameSession);
    }

    @Override
    public Optional<GameSession> getGameSession(String id) {
        return gameSessionRepository.findById(id);
    }

    @Override
    public boolean isSessionComplete(GameSession gameSession) {
        final var questions = questionService.getQuestions(gameSession);
        if (questions.isEmpty()) {
            return false;
        }
        return questions.stream().filter(q -> Objects.isNull(q.getAnswer())).count() == 0;
    }

}
