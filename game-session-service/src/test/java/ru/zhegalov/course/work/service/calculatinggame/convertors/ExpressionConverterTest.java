package ru.zhegalov.course.work.service.calculatinggame.convertors;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import ru.zhegalov.course.work.dto.ExpressionDto;
import ru.zhegalov.course.work.service.calculatinggame.model.ExpressionQuestionData;


public class ExpressionConverterTest {

    @Test
    void shouldConvertToQuestion() {
        final var expression = new ExpressionDto();
        expression.setExpression("1+1");
        expression.setResult(2);
        expression.setValues(Lists.list(1,2,3));
        expression.setOperation("/");

        final var question = ExpressionConverter.createQuestion(expression);

        assertThat(question.getId()).isNull();
        assertThat(question.getAnswer()).isNull();
        assertThat(question.getSessionId()).isNull();
        assertThat(question.getText()).isEqualTo(expression.getExpression());
        assertThat(question.getCorrectAnswer()).isEqualTo(String.valueOf(expression.getResult()));
        assertThat(question.getQuestionData()).isNotNull();
        final var questionData = (ExpressionQuestionData)question.getQuestionData();
        assertThat(questionData.getOperation()).isEqualTo("/");
        assertThat(questionData.getValues()).hasSameElementsAs(Lists.list(1, 2, 3));
    }

}
