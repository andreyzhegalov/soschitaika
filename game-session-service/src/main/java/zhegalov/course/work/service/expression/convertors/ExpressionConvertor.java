package zhegalov.course.work.service.expression.convertors;

import zhegalov.course.work.feign.dto.ExpressionDto;
import zhegalov.course.work.model.Question;

public class ExpressionConvertor {

    public static Question createQuestion(ExpressionDto expression) {
        final var question = new Question();
        question.setText(expression.getExpression());
        question.setCorrectAnswer(String.valueOf(expression.getResult()));
        return question;
    }

}
