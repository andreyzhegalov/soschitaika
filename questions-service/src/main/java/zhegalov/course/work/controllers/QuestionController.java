package zhegalov.course.work.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import zhegalov.course.work.model.Question;

@RestController
public class QuestionController {

    @PostMapping(path = "/api/questions")
    @ResponseStatus(HttpStatus.CREATED)
    public Question createQuestion(){
        return new Question();
    }
}

