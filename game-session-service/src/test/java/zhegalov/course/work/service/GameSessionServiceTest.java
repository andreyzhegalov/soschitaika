package zhegalov.course.work.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.Question;
import zhegalov.course.work.respositories.GameSessionRepository;

@SpringBootTest
public class GameSessionServiceTest {
    @Import(GameSessionServiceImpl.class)
    @Configuration
    public static class TestContext {
    }

    @Autowired
    private GameSessionService sessionService;

    @MockBean
    private GameSessionRepository sessionRepository;

    @MockBean
    private QuestionService questionService;

    @Test
    void shouldCreateNewGameSessionBySettings() {
        given(sessionRepository.save(any())).willReturn(new GameSession());

        final var newSession = sessionService.save(new GameSession());

        then(sessionRepository).should().save(any());
        assertThat(newSession).isNotNull();
    }

    @Test
    void shouldReturnGameSession() {
        String sessionId = "123";
        given(sessionRepository.findById(sessionId)).willReturn(Optional.of(new GameSession()));

        final var gameSession = sessionService.getGameSession(sessionId);

        then(sessionRepository).should().findById(sessionId);
        assertThat(gameSession).isNotEmpty();
    }

    @Test
    void shouldReturnNotCompletedSessionIfQuestionNotFounded() {
        final var gameSession = new GameSession();
        gameSession.setQuestionCount(2);
        given(questionService.getQuestions(gameSession)).willReturn(Collections.emptyList());

        assertThat(sessionService.isSessionComplete(gameSession)).isFalse();

        then(questionService).should().getQuestions(any());
    }

    @Test
    void shouldReturnCompletedSessionIfAllQuestionWithAnswerFounded() {
        final var questionsWithAnswer = Arrays.asList(new Question(), new Question());
        questionsWithAnswer.forEach(q -> q.setAnswer(""));
        final var gameSession = new GameSession();
        gameSession.setQuestionCount(2);
        given(questionService.getQuestions(gameSession)).willReturn(makeQuestionsWithAnswer());

        assertThat(sessionService.isSessionComplete(gameSession)).isTrue();

        then(questionService).should().getQuestions(any());
    }

    @Test
    void souldReturnNotCompletedSessionIfNotAllQuestionWithAnswerFounded() {
        final var gameSession = new GameSession();
        gameSession.setQuestionCount(3);
        given(questionService.getQuestions(gameSession)).willReturn(makeQuestionsWithAnswer());

        assertThat(sessionService.isSessionComplete(gameSession)).isFalse();

        then(questionService).should().getQuestions(any());
    }

    private List<Question> makeQuestionsWithAnswer() {
        final var questionsWithAnswer = Arrays.asList(new Question(), new Question());
        questionsWithAnswer.forEach(q -> q.setAnswer(""));
        return questionsWithAnswer;
    }

    @Test
    void shouldReturnNotCompletedSessionIfSomeQuestionHasNotAnswer() {
        final var questionsWithNotAllAnswers = Arrays.asList(new Question(), new Question());
        questionsWithNotAllAnswers.get(0).setAnswer("");
        final var gameSession = new GameSession();
        gameSession.setQuestionCount(2);
        given(questionService.getQuestions(gameSession)).willReturn(questionsWithNotAllAnswers);

        assertThat(sessionService.isSessionComplete(gameSession)).isFalse();

        then(questionService).should().getQuestions(any());
    }

}
