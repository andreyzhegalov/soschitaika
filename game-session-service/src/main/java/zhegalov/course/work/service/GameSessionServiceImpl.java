package zhegalov.course.work.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.respositories.GameSessionRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class GameSessionServiceImpl implements GameSessionService {
    private final GameSessionRepository gameSessionRepository;
    private final QuestionService questionService;

    @Override
    public GameSession save(GameSession gameSession) {
        return gameSessionRepository.save(gameSession);
    }

    @Override
    public Optional<GameSession> getGameSession(String id) {
        return gameSessionRepository.findById(id);
    }

    @Override
    public boolean isSessionComplete(GameSession gameSession) {
        final var questions = questionService.getQuestions(gameSession);
        log.debug("founded questions {}", questions.size());
        if (questions.isEmpty()) {
            return false;
        }
        final var countQuestionsWithAnswer = questions.stream().filter(q -> Objects.nonNull(q.getAnswer())).count();
        log.debug("founded questions with answer {}", countQuestionsWithAnswer);
        return countQuestionsWithAnswer == gameSession.getQuestionCount();
    }

}
