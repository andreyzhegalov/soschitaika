package ru.zhegalov.course.work.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.zhegalov.course.work.service.GameSessionService;
import ru.zhegalov.course.work.dto.SessionDto;

@RequiredArgsConstructor
@RestController
public class GameSessionController {
    private final GameSessionService sessionService;

    @PostMapping(path = "/api/sessions")
    @ResponseStatus(HttpStatus.CREATED)
    SessionDto saveNewSession(HttpEntity<String> httpEntity) {
        final var bodyString = httpEntity.getBody();
        final var session = sessionService.create(bodyString);
        final var savedSession = sessionService.save(session);
        return new SessionDto(savedSession);
    }
}
