package ru.zhegalov.course.work.service.calculatinggame.convertors;

import ru.zhegalov.course.work.model.Question;
import ru.zhegalov.course.work.dto.ExpressionDto;
import ru.zhegalov.course.work.service.calculatinggame.model.ExpressionQuestionData;

public class ExpressionConverter {

    public static Question createQuestion(ExpressionDto expression) {
        final var question = new Question();
        final var questionData = new ExpressionQuestionData();
        questionData.setOperation(expression.getOperation());
        questionData.setValues(expression.getValues());

        question.setText(expression.getExpression());
        question.setCorrectAnswer(String.valueOf(expression.getResult()));
        question.setQuestionData(questionData);
        return question;
    }

}
