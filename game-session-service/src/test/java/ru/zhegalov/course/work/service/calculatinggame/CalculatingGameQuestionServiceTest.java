package ru.zhegalov.course.work.service.calculatinggame;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.zhegalov.course.work.feign.ExpressionService;
import ru.zhegalov.course.work.model.GameSession;
import ru.zhegalov.course.work.model.Question;
import ru.zhegalov.course.work.model.gamesettings.CalculatingGameSettings;
import ru.zhegalov.course.work.model.gamesettings.CalculationOperation;
import ru.zhegalov.course.work.dto.ExpressionDto;
import ru.zhegalov.course.work.respositories.QuestionRepository;
import ru.zhegalov.course.work.service.QuestionService;

@SpringBootTest
public class CalculatingGameQuestionServiceTest {
    @Import(CalculatingGameQuestionService.class)
    @Configuration
    public static class TestContext {
    }

    @Autowired
    private QuestionService questionService;

    @MockBean
    private ExpressionService expressionService;

    @MockBean
    private QuestionRepository questionRepository;

    @Test
    void shouldReceiveExpressionFromExpressionService() {
        final var gameSession = createSession();
        final var expression = new ExpressionDto();
        expression.setExpression("1+1");
        expression.setResult(2);
        given(expressionService.createExpression(any())).willReturn(expression);

        questionService.createQuestion(gameSession);

        then(expressionService).should().createExpression(any());
        then(questionRepository).should().save(any());
    }

    private GameSession createSession() {
        final var gameSettings = new CalculatingGameSettings();
        gameSettings.setMax(10);
        gameSettings.setMax(1);
        gameSettings.setValueCnt(2);
        gameSettings.setOperations(List.of(CalculationOperation.MUL));
        final var gameSession = new GameSession();
        gameSession.setGameSettings(gameSettings);
        final var sessionId = "sessionId";
        gameSession.setId(sessionId);
        return gameSession;
    }

    @Test
    void shouldSaveNewQuestion() {
        questionService.saveQuestion(new Question());
        then(questionRepository).should().save(any());
    }

    @Test
    void shouldGetAllSessionQuestions() {
        GameSession session = new GameSession();
        final var sessionId = "123";
        session.setId(sessionId);

        questionService.getQuestions(session);

        then(questionRepository).should().findBySessionId(eq(sessionId));
    }

    @Test
    void shouldGetAllQuestionWithAnswer() {
        GameSession session = new GameSession();
        final var sessionId = "123";
        session.setId(sessionId);

        questionService.getQuestionsWithAnswer(session);

        then(questionRepository).should().findBySessionIdAndAnswerIsNotNull(eq(sessionId));
    }

}
