package zhegalov.course.work.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.controllers.dto.SessionDto;
import zhegalov.course.work.model.gamesettings.ExpressionGameSettings;
import zhegalov.course.work.service.GameSessionService;

@RequiredArgsConstructor
@RestController
public class GameSessionController {
    private final GameSessionService sessionService;

    @PostMapping(path = "/api/sessions")
    @ResponseStatus(HttpStatus.CREATED)
    SessionDto saveNewSession(@RequestBody ExpressionGameSettings body){
        return new SessionDto( sessionService.create(body) );
    }
}

