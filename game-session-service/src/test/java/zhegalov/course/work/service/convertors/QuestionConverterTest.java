package zhegalov.course.work.service.convertors;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import zhegalov.course.work.model.Question;

public class QuestionConverterTest {
    @Test
    void shouldCorrectlyConvertQuesionToReportItemDto(){
        final var question = new Question();
        question.setId("id");
        question.setText("text");
        question.setAnswer("answer");
        question.setCorrectAnswer("correctAnswer");
        question.setSessionId("sessionId");

        final var reportItem = new QuestionConverter().convertToReportItem(question);
        assertThat(reportItem).isNotNull();
        assertThat(reportItem.getQuestion()).isEqualTo("text");
        assertThat(reportItem.getAnswer()).isEqualTo("answer");
        assertThat(reportItem.getCorrectAnswer()).isEqualTo("correctAnswer");
    }

    @Test
    void shouldCorrectlySetIsCorrectFieldForCorrectAnswer(){
        final var question = new Question();
        question.setAnswer("answer");
        question.setCorrectAnswer("answer");

        final var reportItem = new QuestionConverter().convertToReportItem(question);
        assertThat(reportItem).isNotNull();
        assertThat(reportItem.isCorrect()).isTrue();
    }

    @Test
    void shouldCorrectlySetIsCorrectFieldForNotCorrectAnswer(){
        final var question = new Question();
        question.setAnswer("answer");
        question.setCorrectAnswer("not correct answer");

        final var reportItem = new QuestionConverter().convertToReportItem(question);
        assertThat(reportItem).isNotNull();
        assertThat(reportItem.isCorrect()).isFalse();
    }

}

