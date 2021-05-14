package zhegalov.course.work.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import zhegalov.course.work.controllers.dto.AnswerDto;
import zhegalov.course.work.model.Question;
import zhegalov.course.work.respositories.QuestionRepository;

@SpringBootTest
public class AnswerServiceImplTest {
    @Configuration
    @Import(AnswerServiceImpl.class)
    public static class TestContext {
    }

    @Autowired
    private AnswerService answerService;

    @MockBean
    private QuestionRepository questionRepository;

    @Test
    void shouldSaveNewAnswerForExistedQuestion() {
        final var questionId = "questionId";
        final var answerDto = new AnswerDto();
        answerDto.setQuestionId(questionId);
        answerDto.setAnswer("answer");
        final var question = new Question();
        question.setId(questionId);
        given(questionRepository.findById(questionId)).willReturn(Optional.of(question));

        assertThat(question.getAnswer()).isNull();

        answerService.saveNewAnswer(answerDto);

        then(questionRepository).should().findById(questionId);
        assertThat(question.getAnswer()).isNotNull();
        then(questionRepository).should().save(any());
    }

    @Test
    void shouldThrowExceptionWhenQuestionForAnswerNotExisted() {
        given(questionRepository.findById(anyString())).willReturn(Optional.empty());

        assertThatCode(() -> answerService.saveNewAnswer(new AnswerDto())).isInstanceOf(GameServiceException.class);

        then(questionRepository).should().findById(any());
    }

    @Test
    void shouldThrowExceptionWhenAnswerCanNotBeSaved() {
        given(questionRepository.findById(anyString())).willReturn(Optional.of(new Question()));
        given(questionRepository.save(any())).willThrow(new GameServiceException());

        assertThatCode(() -> answerService.saveNewAnswer(new AnswerDto())).isInstanceOf(GameServiceException.class);
    }

}
