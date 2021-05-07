package zhegalov.course.work.service;

import zhegalov.course.work.controllers.dto.ExpressionDto;
import zhegalov.course.work.model.GeneratorSetup;
import zhegalov.course.work.model.expression.Expression;

public interface ExpressionGeneratorService {

    Expression create(GeneratorSetup generatorSetup);

    ExpressionDto createExpressionDto(GeneratorSetup generatorSetup);

}
