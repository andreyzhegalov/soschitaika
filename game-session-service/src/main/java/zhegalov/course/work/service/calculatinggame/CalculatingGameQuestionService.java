package zhegalov.course.work.service.calculatinggame;

import java.util.List;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zhegalov.course.work.feign.ExpressionService;
import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.Question;
import zhegalov.course.work.respositories.QuestionRepository;
import zhegalov.course.work.service.QuestionService;
import zhegalov.course.work.service.calculatinggame.convertors.ExpressionConverter;
import zhegalov.course.work.service.calculatinggame.convertors.GameSettingsConverter;

@RequiredArgsConstructor
@Service
public class CalculatingGameQuestionService implements QuestionService {
    private final QuestionRepository questionRepository;
    private final ExpressionService expressionService;
    private OAuth2AuthorizedClient authorizedClient;

    @Override
    public Question createQuestion(GameSession session) {
        final var generatorSetup = GameSettingsConverter.convertGameSettings(session.getGameSettings());
        expressionService.setOAuth2AuthorizedClient(authorizedClient);
        final var expression = expressionService.createExpression(generatorSetup);
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

    @Override
    public void setOAuth2AuthorizedClient(OAuth2AuthorizedClient authorizedClient) {
        this.authorizedClient = authorizedClient;
    }

    @Override
    public List<Question> getQuestionsWithAnswer(GameSession session) {
        return questionRepository.findBySessionIdAndAnswerIsNotNull(session.getId());
    }

}
