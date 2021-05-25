package zhegalov.course.work.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.dto.AnswerDto;
import zhegalov.course.work.service.AnswerService;
import zhegalov.course.work.service.GameServiceException;

@RequiredArgsConstructor
@RestController
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/api/answers")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNewAnswer(@RequestBody AnswerDto answer){
        try {
            answerService.saveNewAnswer(answer);
        } catch (GameServiceException e) {
            throw new NotFoundException();
        }
    }
}

