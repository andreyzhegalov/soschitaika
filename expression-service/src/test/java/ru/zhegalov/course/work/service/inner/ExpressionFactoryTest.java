package ru.zhegalov.course.work.service.inner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ru.zhegalov.course.work.model.expression.ExpressionOperation;
import ru.zhegalov.course.work.service.ExpressionServiceException;

public class ExpressionFactoryTest {
    @Test
    void shouldCreateSumExpression() {
        final var expression = ExpressionFactory.create(ExpressionOperation.SUM, List.of(1, 2, 3));
        assertThat(expression).isNotNull();
        assertThat(expression.getOperation()).isEqualTo("+");
        assertThat(expression.getResult()).isEqualTo(1 + 2 + 3);
    }

    @Test
    void shouldCreateSubExpression() {
        final var expression = ExpressionFactory.create(ExpressionOperation.SUB, List.of(10, 2, 3));
        assertThat(expression).isNotNull();
        assertThat(expression.getOperation()).isEqualTo("-");
        assertThat(expression.getResult()).isEqualTo(10 - 2 - 3);
    }

    @Test
    void shouldCreateMulExpression() {
        final var expression = ExpressionFactory.create(ExpressionOperation.MUL, List.of(1,2,3));
        assertThat(expression).isNotNull();
        assertThat(expression.getOperation()).isEqualTo("*");
        assertThat(expression.getResult()).isEqualTo(1*2*3);
    }

    @Test
    void shouldCreateDivExpression() {
        final var expression = ExpressionFactory.create(ExpressionOperation.DIV, List.of(12, 2, 3));
        assertThat(expression).isNotNull();
        assertThat(expression.getOperation()).isEqualTo("/");
        assertThat(expression.getResult()).isEqualTo(12/2/3);
    }

    @Test
    void shouldThrowExceptionForUndefinedOperation() {
        assertThatCode(() -> ExpressionFactory.create(ExpressionOperation.UNDEFINED, Collections.emptyList()))
                .isInstanceOf(ExpressionServiceException.class);
    }
}
