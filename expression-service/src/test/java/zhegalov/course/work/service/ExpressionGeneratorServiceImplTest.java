package zhegalov.course.work.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import zhegalov.course.work.model.GeneratorSetup;
import zhegalov.course.work.model.expression.Expression;
import zhegalov.course.work.model.expression.ExpressionOperation;

public class ExpressionGeneratorServiceImplTest {

    @Test
    void shouldGenerateExpressionByConfig() {
        final var generatorSetup = new GeneratorSetup();
        generatorSetup.setOperations(ExpressionOperation.SUM);
        generatorSetup.setMin(1);
        generatorSetup.setMax(1);

        final var generator = new ExpressionGeneratorServiceImpl();
        final var expression = generator.create(generatorSetup);

        assertThat(expression).isNotNull().isInstanceOf(Expression.class);
        assertThat(expression.getValues()).hasSize(2);
        assertThat(expression.getResult()).isEqualTo(2);
        assertThat(expression.getValues()).allMatch(v -> v == 1);
    }
}
