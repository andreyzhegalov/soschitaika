package ru.zhegalov.course.work.service.calculatinggame;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.zhegalov.course.work.feign.ExpressionService;
import ru.zhegalov.course.work.model.GameSession;
import ru.zhegalov.course.work.model.Question;
import ru.zhegalov.course.work.respositories.QuestionRepository;
import ru.zhegalov.course.work.service.QuestionService;
import ru.zhegalov.course.work.service.calculatinggame.convertors.ExpressionConverter;
import ru.zhegalov.course.work.service.calculatinggame.convertors.GameSettingsConverter;

@RequiredArgsConstructor
@Service
public class CalculatingGameQuestionService implements QuestionService {
    private final QuestionRepository questionRepository;
    private final ExpressionService expressionService;

    @Override
    public Question createQuestion(GameSession session) {
        final var generatorSetup = GameSettingsConverter.convertGameSettings(session.getGameSettings());
        final var expression = expressionService.createExpression(generatorSetup);
        final var question = ExpressionConverter.createQuestion(expression);
        setSessionId(session, question);
        return saveQuestion(question);
    }

    private void setSessionId(GameSession session, Question question) {
        question.setSessionId(session.getId());
    }

    @Override
    public List<Question> getQuestions(GameSession session) {
        return questionRepository.findBySessionId(session.getId());
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getQuestionsWithAnswer(GameSession session) {
        return questionRepository.findBySessionIdAndAnswerIsNotNull(session.getId());
    }

}
