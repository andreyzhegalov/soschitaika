package zhegalov.course.work.feign;

import zhegalov.course.work.dto.ExpressionDto;
import zhegalov.course.work.dto.GeneratorSetup;

public interface ExpressionService {

    ExpressionDto createExpression(GeneratorSetup generatorSetup);
}
