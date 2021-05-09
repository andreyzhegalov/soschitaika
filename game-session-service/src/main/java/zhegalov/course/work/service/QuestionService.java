package zhegalov.course.work.service;

import java.util.List;

import zhegalov.course.work.model.GameSession;
import zhegalov.course.work.model.Question;

public interface QuestionService {

    List<Question> getQuestions(GameSession session);

}
