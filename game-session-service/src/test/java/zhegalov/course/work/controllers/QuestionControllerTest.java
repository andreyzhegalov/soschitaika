package zhegalov.course.work.controllers;

import static org.hamcrest.Matchers.equalToObject;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.Question;
import zhegalov.course.work.service.GameSessionService;
import zhegalov.course.work.service.QuestionService;

@WebMvcTest(controllers = QuestionController.class)
public class QuestionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private GameSessionService sessionService;

    @Test
    void shouldCreateNewQuestionIfSessionNotCompleted() throws Exception {
        final var gameSessionWithId = new GameSession();
        final var sessionId = "sessionId";
        gameSessionWithId.setId(sessionId);
        final var jsonString = "{\"sessionId\": \"sessionId\"}";
        final var question = new Question();
        question.setText("question text");
        final var savedQuestion = new Question();
        savedQuestion.setId("questionId");
        savedQuestion.setText((question.getText()));

        given(sessionService.getGameSession(sessionId)).willReturn(Optional.of(gameSessionWithId));
        given(sessionService.isSessionComplete(gameSessionWithId)).willReturn(false);
        given(questionService.createQuestion(any())).willReturn(question);
        given(questionService.saveQuestion(eq(question))).willReturn(savedQuestion);

        mvc.perform(
                post("/api/questions").content(jsonString).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.questionId", equalToObject("questionId")))
                .andExpect(jsonPath("$.questionText", equalToObject("question text")));

        then(sessionService).should().getGameSession(eq(sessionId));
        then(sessionService).should().isSessionComplete(gameSessionWithId);
        then(questionService).should().createQuestion(any());
        then(questionService).should().saveQuestion(eq(question));
    }

    @Test
    void shouldReturnNotFoundIfSessionNotFound() throws Exception {
        final var jsonString = "{\"sessionId\": \"sessionId\"}";
        final var sessionId = "sessionId";
        given(sessionService.getGameSession(sessionId)).willReturn(Optional.empty());

        mvc.perform(
                post("/api/questions").content(jsonString).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNotFound());

        then(sessionService).should().getGameSession(eq(sessionId));
        then(questionService).shouldHaveNoInteractions();
    }

    @Test
    void shouldReturnNoContentIfSessionIsCompleted() throws Exception {
        final var gameSessionWithId = new GameSession();
        final var sessionId = "sessionId";
        gameSessionWithId.setId(sessionId);
        final var jsonString = "{\"sessionId\": \"sessionId\"}";

        given(sessionService.getGameSession(sessionId)).willReturn(Optional.of(gameSessionWithId));
        given(sessionService.isSessionComplete(gameSessionWithId)).willReturn(true);

        mvc.perform(
                post("/api/questions").content(jsonString).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNoContent());

        then(sessionService).should().getGameSession(eq(sessionId));
        then(sessionService).should().isSessionComplete(gameSessionWithId);
        then(questionService).shouldHaveNoInteractions();
    }
}
