package zhegalov.course.work.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import zhegalov.course.work.model.Question;

@NoArgsConstructor
@Data
public class QuestionDto {
    private String questionId;
    private String questionText;

    public QuestionDto(Question question){
        this.questionId = question.getId();
        this.questionText = question.getText();
    }
}

