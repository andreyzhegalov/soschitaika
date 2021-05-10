package zhegalov.course.work.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.feign.ExpressionServiceProxy;
import zhegalov.course.work.feign.dto.ExpressionDto;
import zhegalov.course.work.feign.dto.GeneratorSetup;
import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.Question;

@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {
    private final ExpressionServiceProxy expressionServiceProxy;

    @Override
    public Question createQuestion(GameSession session) {
        final var generatorSetup = createGeneratorSetup(session);
        final var expression = expressionServiceProxy.createExpression(generatorSetup);
        return createQuestion(expression);
    }

    private GeneratorSetup createGeneratorSetup(GameSession gameSession) {
        return null;
    }

    private Question createQuestion(ExpressionDto expression) {
        return null;
    }

    @Override
    public List<Question> getQuestions(GameSession session) {
        // TODO Auto-generated method stub
        return null;
    }

}
