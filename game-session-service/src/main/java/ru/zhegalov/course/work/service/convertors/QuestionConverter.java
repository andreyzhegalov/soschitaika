package ru.zhegalov.course.work.service.convertors;

import org.springframework.stereotype.Component;

import ru.zhegalov.course.work.model.Question;
import ru.zhegalov.course.work.dto.ReportItemDto;

@Component
public class QuestionConverter {

    public ReportItemDto convertToReportItem(Question question) {
        final var reportItem = new ReportItemDto();
        reportItem.setQuestion(question.getText());
        reportItem.setAnswer(question.getAnswer());
        reportItem.setCorrectAnswer(question.getCorrectAnswer());
        reportItem.setCorrect(isCorrectAnswer(question));
        return reportItem;
    }

    private boolean isCorrectAnswer(Question question) {
        return question.getAnswer().equals(question.getCorrectAnswer());
    }

}
