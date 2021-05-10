package zhegalov.course.work.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import zhegalov.course.work.feign.ExpressionServiceProxy;
import zhegalov.course.work.feign.dto.ExpressionDto;
import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.respositories.QuestionRepository;

@SpringBootTest
public class QuestionServiceImplTest {
    @Import(QuestionServiceImpl.class)
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
        final var gameSession = new GameSession();
        given(expressionService.createExpression(any())).willReturn(new ExpressionDto());

        final var questions = questionService.createQuestion(gameSession);
        assertThat(questions).isNotNull();

        then(expressionService).should().createExpression(any());
    }

    @Test
    void shouldSaveNewQuestion(){
         questionService.createQuestion(new GameSession());
         then(questionRepository).should().save(any());
    }

}

