package zhegalov.course.work.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import zhegalov.course.work.model.Question;

public class QuestionGeneratorServiceTest {

    @Test
    void shouldGenerateQuestion() {
        final QuestionGeneratorService generator = new QuestionGeneratorServiceImpl();
        final var question = generator.create();
        assertThat(question).isNotNull().isInstanceOf(Question.class);
    }
}
