package zhegalov.course.work.service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final QuestionService questionService;

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

    @Override
    public boolean isSessionComplete(GameSession gameSession) {
        final var questions = questionService.getQuestions(gameSession);
        if(questions.isEmpty()){
            return false;
        }
        return questions.stream().filter(q -> Objects.isNull(q.getAnswer())).count() == 0;
    }

}
