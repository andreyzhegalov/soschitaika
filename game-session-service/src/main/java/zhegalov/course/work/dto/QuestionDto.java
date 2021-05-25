package zhegalov.course.work.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import zhegalov.course.work.model.Question;
import zhegalov.course.work.model.QuestionData;

@NoArgsConstructor
@Data
public class QuestionDto {
    private String questionId;
    private String questionText;
    private QuestionData questionData;

    public QuestionDto(Question question){
        this.questionId = question.getId();
        this.questionText = question.getText();
        this.questionData = question.getQuestionData();
    }
}

