package ru.zhegalov.course.work.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import ru.zhegalov.course.work.dto.ExpressionDto;
import ru.zhegalov.course.work.dto.GeneratorSetup;

@FeignClient(name = "expression-service", url = "${expression-server.url}")
public interface ExpressionService {

    @PostMapping(path = "/api/expressions")
    @ResponseStatus(HttpStatus.CREATED)
    ExpressionDto createExpression(GeneratorSetup generatorSetup);
}
