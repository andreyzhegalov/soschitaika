package ru.zhegalov.course.work.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.zhegalov.course.work.model.Question;
import ru.zhegalov.course.work.respositories.QuestionRepository;
import ru.zhegalov.course.work.dto.AnswerDto;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {
    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public Question saveNewAnswer(AnswerDto answerDto) {
        final var question = questionRepository.findById(answerDto.getQuestionId()).orElseThrow(
                () -> new GameServiceException("Question with id " + answerDto.getQuestionId() + " not exist"));
        question.setAnswer(answerDto.getAnswer());
        return questionRepository.save(question);
    }

}
