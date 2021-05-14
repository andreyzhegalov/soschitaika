package zhegalov.course.work.service.calculatinggame;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.feign.ExpressionServiceProxy;
import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.Question;
import zhegalov.course.work.respositories.QuestionRepository;
import zhegalov.course.work.service.QuestionService;
import zhegalov.course.work.service.calculatinggame.convertors.ExpressionConverter;
import zhegalov.course.work.service.calculatinggame.convertors.GameSettingsConverter;

@RequiredArgsConstructor
@Service
public class CalculatingGameQuestionService implements QuestionService {
    @Autowired
    private ExpressionServiceProxy expressionServiceProxy;
    private final QuestionRepository questionRepository;

    @Override
    public Question createQuestion(GameSession session) {
        final var generatorSetup = GameSettingsConverter.convertGameSettings(session.getGameSettings());
        final var expression = expressionServiceProxy.createExpression(generatorSetup);
        final var question = ExpressionConverter.createQuestion(expression);
        setSessionId(session, question);
        return question;
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
}
