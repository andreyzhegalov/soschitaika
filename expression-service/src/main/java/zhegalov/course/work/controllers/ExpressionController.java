package zhegalov.course.work.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.model.GeneratorSetup;
import zhegalov.course.work.model.expression.Expression;
import zhegalov.course.work.service.ExpressionGeneratorService;

@RequiredArgsConstructor
@RestController
public class ExpressionController {

    private final ExpressionGeneratorService  expressionGeneratorService;

    @PostMapping(path = "/api/expressions")
    @ResponseStatus(HttpStatus.CREATED)
    public Expression createExpression(@RequestBody GeneratorSetup generatorSetup){
        return expressionGeneratorService.create(generatorSetup);
    }
}

