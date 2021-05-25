package zhegalov.course.work.service.calculatinggame.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import zhegalov.course.work.model.QuestionData;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ExpressionQuestionData extends QuestionData {
    private String operation;
    private List<Integer> values;
}
