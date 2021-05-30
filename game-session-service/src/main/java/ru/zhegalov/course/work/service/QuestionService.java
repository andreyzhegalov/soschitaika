package ru.zhegalov.course.work.service;

import java.util.List;

import ru.zhegalov.course.work.model.GameSession;
import ru.zhegalov.course.work.model.Question;

public interface QuestionService {

    Question createQuestion(GameSession session);

    Question saveQuestion(Question question);

    List<Question> getQuestions(GameSession session);

    List<Question> getQuestionsWithAnswer(GameSession session);

}
