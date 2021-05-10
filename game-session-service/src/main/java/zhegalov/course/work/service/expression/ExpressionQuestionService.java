package zhegalov.course.work.service.expression;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.feign.ExpressionServiceProxy;
import zhegalov.course.work.feign.dto.ExpressionDto;
import zhegalov.course.work.feign.dto.GeneratorSetup;
import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.Question;
import zhegalov.course.work.model.gamesettings.ExpressionGameSettings;
import zhegalov.course.work.service.QuestionService;

@RequiredArgsConstructor
@Service
public class ExpressionQuestionService implements QuestionService {
    private final ExpressionServiceProxy expressionServiceProxy;

    @Override
    public Question createQuestion(GameSession session) {
        final var generatorSetup = createGeneratorSetup(session);
        final var expression = expressionServiceProxy.createExpression(generatorSetup);
        return createQuestion(expression);
    }

    private GeneratorSetup createGeneratorSetup(GameSession gameSession) {
        final var gameSettings = (ExpressionGameSettings) gameSession.getGameSettings();
        final var generatorSetup = new GeneratorSetup();
        generatorSetup.setMax(gameSettings.getMax());
        generatorSetup.setMin(gameSettings.getMin());
        generatorSetup.setValueCnt(gameSettings.getValueCnt());
        // final var operations = gameSettings.getOperations().stream().map(o -> (ExpressionOperation) o)
        //         .collect(Collectors.toList());
        // generatorSetup.setOperations(gameSettings.getOperations());
        return generatorSetup;
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
