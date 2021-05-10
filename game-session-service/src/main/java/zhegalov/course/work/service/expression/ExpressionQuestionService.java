package zhegalov.course.work.service.expression;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.feign.ExpressionServiceProxy;
import zhegalov.course.work.feign.dto.ExpressionDto;
import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.Question;
import zhegalov.course.work.service.QuestionService;
import zhegalov.course.work.service.expression.convertors.GameSettingsConvertor;

@RequiredArgsConstructor
@Service
public class ExpressionQuestionService implements QuestionService {
    private final ExpressionServiceProxy expressionServiceProxy;

    @Override
    public Question createQuestion(GameSession session) {
        final var generatorSetup = GameSettingsConvertor.convertGameSettings(session.getGameSettings());
        final var expression = expressionServiceProxy.createExpression(generatorSetup);
        return createQuestion(expression);
    }

    private Question createQuestion(ExpressionDto expression) {
        final var question =  new Question();
        question.setText(expression.getExpression());
        return question;
    }

    @Override
    public List<Question> getQuestions(GameSession session) {
        // TODO Auto-generated method stub
        return null;
    }

}
