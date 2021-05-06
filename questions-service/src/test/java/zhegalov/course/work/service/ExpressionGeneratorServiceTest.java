package zhegalov.course.work.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import zhegalov.course.work.model.Expression;
import zhegalov.course.work.model.ExpressionOperation;
import zhegalov.course.work.model.GeneratorSetup;

public class ExpressionGeneratorServiceTest {

    @Test
    void shouldGenerateSumExpression() {
        final var generator = new ExpressionGeneratorServiceImpl();
        final var generatorSetup = new GeneratorSetup();
        generatorSetup.setOperations(ExpressionOperation.SUM);
        generatorSetup.setMin(1);
        generatorSetup.setMax(1);

        final var expression = generator.create(generatorSetup);
        assertThat(expression).isNotNull().isInstanceOf(Expression.class);
    }
}
