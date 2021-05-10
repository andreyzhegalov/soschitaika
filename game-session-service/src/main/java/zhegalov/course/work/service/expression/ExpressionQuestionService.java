package zhegalov.course.work.service.expression;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.feign.ExpressionServiceProxy;
import zhegalov.course.work.feign.dto.ExpressionDto;
import zhegalov.course.work.model.Answer;
import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.Question;
import zhegalov.course.work.respositories.QuestionRepository;
import zhegalov.course.work.service.QuestionService;
import zhegalov.course.work.service.expression.convertors.GameSettingsConvertor;

@RequiredArgsConstructor
@Service
public class ExpressionQuestionService implements QuestionService {
    @Autowired
    private ExpressionServiceProxy expressionServiceProxy;
    private final QuestionRepository questionRepository;

    @Override
    public Question createQuestion(GameSession session) {
        final var generatorSetup = GameSettingsConvertor.convertGameSettings(session.getGameSettings());
        final var expression = expressionServiceProxy.createExpression(generatorSetup);
        final var question = createQuestion(expression);
        question.setSessionId(session.getId());
        return question;
    }

    private Question createQuestion(ExpressionDto expression) {
        final var question = new Question();
        question.setText(expression.getExpression());
        question.setAnswer(new Answer(String.valueOf(expression.getResult())));
        return question;
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
