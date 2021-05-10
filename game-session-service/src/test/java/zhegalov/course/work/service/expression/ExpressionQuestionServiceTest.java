package zhegalov.course.work.service.expression;

import static org.assertj.core.api.Assertions.assertThat;
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

import zhegalov.course.work.feign.ExpressionServiceProxy;
import zhegalov.course.work.feign.dto.ExpressionDto;
import zhegalov.course.work.model.Answer;
import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.Question;
import zhegalov.course.work.model.gamesettings.ExpressionGameSettings;
import zhegalov.course.work.model.gamesettings.ExpressionOperationV0;
import zhegalov.course.work.respositories.QuestionRepository;
import zhegalov.course.work.service.QuestionService;

@SpringBootTest
public class ExpressionQuestionServiceTest {
    @Import(ExpressionQuestionService.class)
    @Configuration
    public static class TestContext {
    }

    @Autowired
    private QuestionService questionService;

    @MockBean
    private ExpressionServiceProxy expressionService;

    @MockBean
    private QuestionRepository questionRepository;

    @Test
    void shouldReciveExpressionFromExpressionService() {
        final var gameSettings = new ExpressionGameSettings();
        gameSettings.setMax(10);
        gameSettings.setMax(1);
        gameSettings.setValueCnt(2);
        gameSettings.setOperations(List.of(ExpressionOperationV0.MUL));
        final var gameSession = new GameSession();
        gameSession.setGameSettings(gameSettings);
        final var sessionId = "sessionId";
		gameSession.setId(sessionId);

        final var expression = new ExpressionDto();
        expression.setExpression("1+1");
        expression.setResult(2);

        given(expressionService.createExpression(any())).willReturn(expression);

        final var questions = questionService.createQuestion(gameSession);

        assertThat(questions).isNotNull();
        assertThat(questions.getText()).isEqualTo(expression.getExpression());
        assertThat(questions.getAnswer()).isEqualTo(new Answer("2"));
        assertThat(questions.getSessionId()).isEqualTo(sessionId);
        then(expressionService).should().createExpression(any());
    }

    @Test
    void shouldSaveNewQuestion() {
        questionService.saveQuestion(new Question());
        then(questionRepository).should().save(any());
    }

    @Test
    void shouldGetAllSessionQuestions(){
        GameSession session = new GameSession();
        final var sessionId = "123";
		session.setId(sessionId);

		questionService.getQuestions(session);

        then(questionRepository).should().findBySessionId(eq(sessionId));
    }

}
