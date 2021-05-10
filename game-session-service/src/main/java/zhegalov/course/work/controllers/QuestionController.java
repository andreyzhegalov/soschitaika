package zhegalov.course.work.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.controllers.dto.QuestionDto;
import zhegalov.course.work.controllers.dto.SessionDto;
import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.service.QuestionService;

@RequiredArgsConstructor
@RestController
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/api/questions")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionDto createQuestion(@RequestBody SessionDto session){
        final var gameSession = new GameSession();
		final var question = questionService.createQuestion(gameSession);
        return new QuestionDto(question);
    }

}

