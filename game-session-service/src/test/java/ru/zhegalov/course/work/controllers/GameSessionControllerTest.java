package ru.zhegalov.course.work.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ru.zhegalov.course.work.model.GameSession;
import ru.zhegalov.course.work.model.gamesettings.CalculatingGameSettings;
import ru.zhegalov.course.work.model.gamesettings.CalculationOperation;
import ru.zhegalov.course.work.service.GameSessionService;

@WebMvcTest(controllers = GameSessionController.class)
@AutoConfigureMockMvc(addFilters = false)
public class GameSessionControllerTest {
    @MockBean
    private GameSessionService sessionService;

    @Autowired
    private MockMvc mvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldSaveNewSession() throws Exception {
        final var gameSession = createGameSession();
        final var jsonString = mapper.writeValueAsString(gameSession);

        given(sessionService.create(anyString())).willReturn(getSavedGameSession());
        given(sessionService.save(any())).willReturn(getSavedGameSession());

        mvc.perform(
                post("/api/sessions").content(jsonString).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());

        then(sessionService).should().create(any());
        then(sessionService).should().save(any());
    }

    private GameSession createGameSession() {
        final var generatorSetup = new CalculatingGameSettings();
        generatorSetup.setMin(0);
        generatorSetup.setMax(1);
        generatorSetup.setValueCnt(5);
        generatorSetup.setOperations(List.of(CalculationOperation.SUM));
        final var gameSession = new GameSession();
        gameSession.setQuestionCount(2);
        gameSession.setGameSettings(generatorSetup);
        return gameSession;
    }

    private GameSession getSavedGameSession() {
        final var gameSession = createGameSession();
        gameSession.setId("some id");
        return gameSession;
    }
}
