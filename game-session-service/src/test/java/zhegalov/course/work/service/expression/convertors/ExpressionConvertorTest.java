package zhegalov.course.work.service.expression.convertors;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import zhegalov.course.work.feign.dto.ExpressionDto;

public class ExpressionConvertorTest {

    @Test
    void shouldConvertToQuestion() {
        ExpressionDto expression = new ExpressionDto();
        expression.setExpression("1+1");
        expression.setResult(2);

        final var question = ExpressionConvertor.createQuestion(expression);

        assertThat(question.getId()).isNull();
        assertThat(question.getAnswer()).isNull();
        assertThat(question.getSessionId()).isNull();
        assertThat(question.getText()).isEqualTo(expression.getExpression());
        assertThat(question.getCorrectAnswer()).isEqualTo(String.valueOf(expression.getResult()));
    }

}
