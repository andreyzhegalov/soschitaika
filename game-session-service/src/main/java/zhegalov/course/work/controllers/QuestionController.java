package zhegalov.course.work.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.dto.QuestionDto;
import zhegalov.course.work.dto.SessionDto;
import zhegalov.course.work.service.GameSessionService;
import zhegalov.course.work.service.QuestionService;

@RequiredArgsConstructor
@RestController
public class QuestionController {
    private final QuestionService questionService;
    private final GameSessionService sessionService;

    @PostMapping("/api/questions")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionDto createQuestion(@RequestBody SessionDto session){

        final var gameSession = sessionService.getGameSession(session.getSessionId());
        if (gameSession.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "session with id " + session.getSessionId() + " not found");
        }
        if (sessionService.isSessionComplete(gameSession.get())) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,
                    "session with id " + session.getSessionId() + " is completed");
        }
        final var question = questionService.createQuestion(gameSession.get());
        final var savedQuestion = questionService.saveQuestion(question);
        return new QuestionDto(savedQuestion);
    }

}
