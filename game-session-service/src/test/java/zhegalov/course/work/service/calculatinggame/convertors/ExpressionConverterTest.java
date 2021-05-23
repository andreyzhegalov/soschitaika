package zhegalov.course.work.service.calculatinggame.convertors;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import zhegalov.course.work.controllers.dto.ExpressionDto;


public class ExpressionConverterTest {

    @Test
    void shouldConvertToQuestion() {
        final var expression = new ExpressionDto();
        expression.setExpression("1+1");
        expression.setResult(2);

        final var question = ExpressionConverter.createQuestion(expression);

        assertThat(question.getId()).isNull();
        assertThat(question.getAnswer()).isNull();
        assertThat(question.getSessionId()).isNull();
        assertThat(question.getText()).isEqualTo(expression.getExpression());
        assertThat(question.getCorrectAnswer()).isEqualTo(String.valueOf(expression.getResult()));
    }

}
