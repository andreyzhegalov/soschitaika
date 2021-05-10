package zhegalov.course.work.service;

import java.util.List;

import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.Question;

public interface QuestionService {

    Question createQuestion(GameSession session);

    List<Question> getQuestions(GameSession session);

}
