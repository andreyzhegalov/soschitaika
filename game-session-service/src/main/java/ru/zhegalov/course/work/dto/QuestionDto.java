package ru.zhegalov.course.work.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.zhegalov.course.work.model.Question;
import ru.zhegalov.course.work.model.QuestionData;

@NoArgsConstructor
@Getter
@Setter
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

