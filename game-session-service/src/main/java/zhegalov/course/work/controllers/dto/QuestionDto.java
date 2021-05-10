package zhegalov.course.work.controllers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import zhegalov.course.work.model.Question;

@NoArgsConstructor
@Data
public class QuestionDto {
    private String questionText;

    public QuestionDto(Question question){
        this.questionText = question.getText();
    }
}

