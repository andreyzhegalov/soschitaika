package zhegalov.course.work.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.dto.AnswerDto;
import zhegalov.course.work.model.Question;
import zhegalov.course.work.respositories.QuestionRepository;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {
    private final QuestionRepository questionRepository;

    @Override
    public Question saveNewAnswer(AnswerDto answerDto) {
        final var question = questionRepository.findById(answerDto.getQuestionId()).orElseThrow(
                () -> new GameServiceException("Question with id " + answerDto.getQuestionId() + " not exist"));
        question.setAnswer(answerDto.getAnswer());
        return questionRepository.save(question);
    }

}
