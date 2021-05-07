package zhegalov.course.work.service;

import zhegalov.course.work.model.GeneratorSetup;
import zhegalov.course.work.model.expression.Expression;

public interface ExpressionGeneratorService {

    Expression create(GeneratorSetup generatorSetup);

}
