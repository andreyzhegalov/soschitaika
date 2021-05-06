package zhegalov.course.work.service;

import zhegalov.course.work.model.Expression;
import zhegalov.course.work.model.GeneratorSetup;

public interface ExpressionGeneratorService {

    Expression create(GeneratorSetup generatorSetup);

}
