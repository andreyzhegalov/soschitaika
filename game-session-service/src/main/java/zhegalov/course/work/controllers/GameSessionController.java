package zhegalov.course.work.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.dto.SessionDto;
import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.GameSettings;
import zhegalov.course.work.model.gamesettings.CalculatingGameSettings;
import zhegalov.course.work.service.GameSessionService;

@RequiredArgsConstructor
@RestController
public class GameSessionController {
    private final static String QUESTION_COUNT_NODE = "questionCount";
    private final static String GAME_SETTINGS_NODE = "gameSettings";
    private final GameSessionService sessionService;

    @GetMapping(path = "/api/sessions")
    @ResponseStatus(HttpStatus.OK)
    SessionDto getNewSession(){
        return new SessionDto();
    }

    @PostMapping(path = "/api/sessions")
    @ResponseStatus(HttpStatus.CREATED)
    SessionDto saveNewSession(HttpEntity<String> httpEntity)
            throws JsonMappingException, JsonProcessingException {
        final var bodyString = httpEntity.getBody();
        final var settings = getGameSettings(bodyString, CalculatingGameSettings.class);

        final var session = new GameSession();
        session.setGameSettings(settings);
        session.setQuestionCount(getQuestionCount(bodyString));
        final var savedSession = sessionService.save(session);
        return new SessionDto(savedSession);
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
