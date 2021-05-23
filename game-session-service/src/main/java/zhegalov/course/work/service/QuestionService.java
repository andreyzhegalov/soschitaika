package zhegalov.course.work.service;

import java.util.List;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.Question;

public interface QuestionService {

    Question createQuestion(GameSession session);

    Question saveQuestion(Question question);

    List<Question> getQuestions(GameSession session);

    void setOAuth2AuthorizedClient(OAuth2AuthorizedClient authorizedClient);

}
