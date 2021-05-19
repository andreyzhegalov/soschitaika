package zhegalov.course.work.service.convertors;

import zhegalov.course.work.feign.dto.ReportItemDto;
import zhegalov.course.work.model.Question;

public class QuestionConverter {

    public static ReportItemDto convertToReportItem(Question question) {
        final var reportItem = new ReportItemDto();
        reportItem.setQuestion(question.getText());
        reportItem.setAnswer(question.getAnswer());
        reportItem.setCorrectAnswer(question.getCorrectAnswer());
        reportItem.setCorrect(isCorrectAnswer(question));
        return reportItem;
    }

    private static boolean isCorrectAnswer(Question question) {
        return question.getAnswer() == question.getCorrectAnswer();
    }

}
