package ru.zhegalov.course.work.service.calculatinggame.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.zhegalov.course.work.model.QuestionData;

@NoArgsConstructor
@Getter
@Setter
public class ExpressionQuestionData extends QuestionData {
    private String operation;
    private List<Integer> values;
}
