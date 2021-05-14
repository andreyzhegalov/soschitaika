package zhegalov.course.work.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.controllers.dto.AnswerDto;
import zhegalov.course.work.model.Question;
import zhegalov.course.work.respositories.QuestionRepository;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {
    private final QuestionRepository questionRepository;

    @Override
    public Question saveNewAnswer(AnswerDto answerDto) {
        final var mayBeQuestion = questionRepository.findById(answerDto.getQuestionId());
        if (mayBeQuestion.isEmpty()) {
            throw new GameServiceException("Question with id " + answerDto.getQuestionId() + " not exist");
        }
        final var question = mayBeQuestion.get();
        question.setAnswer(answerDto.getAnswer());
        return questionRepository.save(question);
    }

}
