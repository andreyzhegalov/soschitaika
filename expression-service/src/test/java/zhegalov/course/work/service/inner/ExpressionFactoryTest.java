package zhegalov.course.work.service.inner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import zhegalov.course.work.model.expression.ExpressionOperation;
import zhegalov.course.work.service.ExpressionServiceException;

public class ExpressionFactoryTest {
    @Test
    void shouldCreateSumExpression() {
        final var expression = ExpressionFactory.create(ExpressionOperation.SUM, Collections.emptyList());
        assertThat(expression).isNotNull();
        assertThat(expression.getOperation()).isEqualTo("+");
    }

    @Test
    void shouldCreateSubExpression() {
        final var expression = ExpressionFactory.create(ExpressionOperation.SUB, Collections.emptyList());
        assertThat(expression).isNotNull();
        assertThat(expression.getOperation()).isEqualTo("-");
    }

    @Test
    void shouldCreateMulExpression() {
        final var expression = ExpressionFactory.create(ExpressionOperation.MUL, Collections.emptyList());
        assertThat(expression).isNotNull();
        assertThat(expression.getOperation()).isEqualTo("*");
    }

    @Test
    void shouldCreateDivExpression() {
        final var expression = ExpressionFactory.create(ExpressionOperation.DIV, Collections.emptyList());
        assertThat(expression).isNotNull();
        assertThat(expression.getOperation()).isEqualTo("/");
    }

    @Test
    void shouldThrowExceptionForUndefinedOperation() {
        assertThatCode(() -> ExpressionFactory.create(ExpressionOperation.UNDEFINED, Collections.emptyList()))
                .isInstanceOf(ExpressionServiceException.class);
    }
}
