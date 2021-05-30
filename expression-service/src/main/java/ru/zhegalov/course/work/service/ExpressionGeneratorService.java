package ru.zhegalov.course.work.service;

import ru.zhegalov.course.work.dto.ExpressionDto;
import ru.zhegalov.course.work.model.GeneratorSetup;
import ru.zhegalov.course.work.model.expression.Expression;

public interface ExpressionGeneratorService {

    Expression create(GeneratorSetup generatorSetup);

    ExpressionDto createExpressionDto(GeneratorSetup generatorSetup);

}
