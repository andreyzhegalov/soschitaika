package zhegalov.course.work.service;

import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.GameSettings;
import zhegalov.course.work.model.gamesettings.CalculatingGameSettings;
import zhegalov.course.work.respositories.GameSessionRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class GameSessionServiceImpl implements GameSessionService {
    private final static String QUESTION_COUNT_NODE = "questionCount";
    private final static String GAME_SETTINGS_NODE = "gameSettings";
    private final GameSessionRepository gameSessionRepository;
    private final QuestionService questionService;

    @Override
    public GameSession create(String jsonData) {
        final var session = new GameSession();
        try {
            final var settings = getGameSettings(jsonData, CalculatingGameSettings.class);
            session.setGameSettings(settings);
            session.setQuestionCount(getQuestionCount(jsonData));
        } catch (JsonProcessingException e) {
            throw new GameServiceException(e);
        }
        return session;
    }

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

    private int getQuestionCount(String bodyString) throws JsonMappingException, JsonProcessingException {
        final var objectMapper = new ObjectMapper();
        final var jsonNode = objectMapper.readTree(bodyString);
        return jsonNode.get(QUESTION_COUNT_NODE).asInt();
    }

    private GameSettings getGameSettings(String bodyString, Class<? extends GameSettings> gameSettingsClass)
            throws JsonMappingException, JsonProcessingException {
        final var objectMapper = new ObjectMapper();
        final var jsonNode = objectMapper.readTree(bodyString);
        return (GameSettings) objectMapper.treeToValue(jsonNode.get(GAME_SETTINGS_NODE), gameSettingsClass);
    }

}
