package ru.zhegalov.course.work.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import ru.zhegalov.course.work.model.expression.SumExpression;
import ru.zhegalov.course.work.model.expression.SubExpression;


public class ExpressionPrintServiceImplTest {
    @Test
    void shouldPrintSumExpression() {
        final var expression = new SumExpression(List.of(1, 2, 3));
        assertThat(new ExpressionPrintServiceImpl().print(expression)).isEqualTo("1+2+3");
    }

    @Test
    void shouldPrintSubExpression() {
        final var expression = new SubExpression(List.of(3, 2, 1));
        assertThat(new ExpressionPrintServiceImpl().print(expression)).isEqualTo("3-2-1");
    }
}
