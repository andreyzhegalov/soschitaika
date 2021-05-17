package zhegalov.course.work.service.calculatinggame;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import zhegalov.course.work.controllers.dto.ExpressionDto;
import zhegalov.course.work.controllers.dto.GeneratorSetup;
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
    private final QuestionRepository questionRepository;
    private final ExpressionServiceProxy expressionService;

    @Override
	public Question createQuestion(GameSession session, OAuth2AuthorizedClient authorizedClient) {
        final var generatorSetup = GameSettingsConverter.convertGameSettings(session.getGameSettings());
        final var expression = expressionService.createExpression(generatorSetup, authorizedClient);

		// final var expression = this.webClient
		//         .post()
		//         .uri(messagesBaseUri)
		//         .attributes(oauth2AuthorizedClient(authorizedClient))
        //         .body(Mono.just(generatorSetup), GeneratorSetup.class)
		//         .retrieve()
		//         .bodyToMono(ExpressionDto.class)
		//         .block();

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
