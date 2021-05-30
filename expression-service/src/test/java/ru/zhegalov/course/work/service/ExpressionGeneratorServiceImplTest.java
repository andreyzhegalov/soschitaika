package ru.zhegalov.course.work.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.zhegalov.course.work.model.GeneratorSetup;
import ru.zhegalov.course.work.model.expression.Expression;
import ru.zhegalov.course.work.model.expression.ExpressionOperation;

@SpringBootTest
public class ExpressionGeneratorServiceImplTest {
    @Configuration
    @Import(ExpressionGeneratorServiceImpl.class)
    public static class TestContext {
    }

    @Autowired
    private ExpressionGeneratorService generator;

    @MockBean
    private ExpressionPrintService printService;

    private static GeneratorSetup generatorSetup;

    @BeforeAll
    static void setUp() {
        generatorSetup = new GeneratorSetup();
        generatorSetup.setOperations(ExpressionOperation.SUM);
        generatorSetup.setMin(1);
        generatorSetup.setMax(1);
    }

    @Test
    void shouldGenerateExpressionByConfig() {
        final var expression = generator.create(generatorSetup);

        assertThat(expression).isNotNull().isInstanceOf(Expression.class);
        assertThat(expression.getValues()).hasSize(2);
        assertThat(expression.getResult()).isEqualTo(2);
        assertThat(expression.getValues()).allMatch(v -> v == 1);
    }

    @Test
    void shouldCreateExpressionDto() {
        given(printService.print(any())).willReturn("1+1");

        final var expressionDto = generator.createExpressionDto(generatorSetup);

        then(printService).should().print(any());
        assertThat(expressionDto).isNotNull();
        assertThat(expressionDto.getExpression()).isEqualTo("1+1");
        assertThat(expressionDto.getResult()).isEqualTo(1 + 1);
    }
}
