package zhegalov.course.work.service.convertors;

import org.springframework.stereotype.Component;

import zhegalov.course.work.dto.ReportItemDto;
import zhegalov.course.work.model.Question;

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
